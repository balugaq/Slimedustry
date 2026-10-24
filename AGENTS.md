# AGENTS.md — Slimedustry

给 AI 代理（和接手的人类）看的项目说明书。先读「核心机制」和「已知陷阱」，再动手。

## 1. 项目是什么

**Slimedustry** 是 [Slimefun4](https://github.com/Slimefun/Slimefun4) 的一个**工业主题扩展插件（addon）**。它本身不实现服务端，而是挂到 Slimefun 上，往游戏里新增一套工业线物品：多级多方块机器、新材料（钛/铱/树脂）、储罐、纳米工厂套装、激光采矿器、小型核弹等。

- 一句话：给 Slimefun 加一套「工厂合成台 → 压板机 → 零件工厂」工业科技树。
- 没有 Slimefun 本体，本插件无法启动（见 `Slimedustry.onEnable` 的硬依赖检查）。

## 2. 技术栈与运行环境

| 项 | 值 |
|---|---|
| 语言 | Java 21（`maven.compiler.source/target = 21`） |
| 服务端 | Spigot / Paper API `1.21.1-R0.1-SNAPSHOT`（`provided`） |
| 依赖插件 | **Slimefun4 `2025.1`**（`provided`，来自 jitpack） |
| 构建 | Maven（`pom.xml`） |
| 插件格式 | 标准 Bukkit 插件（`plugin.yml` + `config.yml`） |
| 坐标 | `me.diamondman121314:Slimedustry:1.3.0` |

> ⚠️ Slimefun4 版本被**锁定在 2025.1**。代码里直接调用了 `io.github.thebusybiscuit.slimefun4.implementation.Slimefun` 等 implementation 包内部类，升级 Slimefun 极可能编译/运行失败，需要先排查内部 API 变动。

## 3. 构建与运行

```bash
mvn clean package
```

- 产出：`target/Slimedustry v1.3.0.jar`（注意文件名带空格，来自 `finalName = ${project.name} v${project.version}`）。
- 同时产出 `-sources.jar`（由 `maven-source-plugin` 的 `attach-sources` 生成）。
- 把主 jar 丢进服务端的 `plugins/` 目录，且**必须同时安装 Slimefun4**，重启生效。
- 无单元测试、无 CI。

## 4. 代码结构（按职责）

```
src/main/java/me/diamondman121314/Slimedustry/
├── Slimedustry.java           主类，implements SlimefunAddon
├── Setup.java                 所有物品/配方类型/物品组/研究的总装配表
├── Commands.java              /slimedustry 命令（reload / charge）
├── RechargeableItem.java      可充电物品基类（封装 capacity 电容）
├── MassFabricator.java        零件工厂多方块机器（占位壳，逻辑在 Listener 里手搓）
└── Listeners/
    ├── BlockListener.java     方块破坏/放置/树叶衰败/发射器事件
    ├── InteractListener.java  多方块机器合成、储罐液体、激光采矿器发射
    ├── InventoryListener.java 锁住储罐库存、阻止液体被漏斗抽走
    └── EntitiesListener.java  激光雪球命中、静电靴充能、纳米套装减伤、橡胶靴防摔

src/main/resources/
├── plugin.yml                 插件元数据、命令、权限
└── config.yml                 可调数值（爆炸强度、各模式耗电、掉落率等）
```

### 关键类职责

- **`Setup`** —— 整个插件的内容目录。所有 `SlimefunItemStack` 静态常量、8 个 `ItemGroup`、4 个 `RecipeType`、`setupItems()` 注册全部物品并 `addRecipe`，`setupResearches()` 注册研究。
  - ⚠️ `setupItems()` 里大量**「借用」Slimefun 原版配方**：通过 `SlimefunItem.getByItem(SlimefunItems.XXX).getRecipe()` 把原版喷气背包、护甲、电路板的合成重定向到本插件的工厂合成台。若对应 `SlimefunItems` 字段为 null 会 NPE。
  - `changeRecipes()` / `changeCategories()` 在 `onEnable` 里**被注释、未启用**，但部分逻辑已被 `setupItems` 的 `addRecipe` 手动覆盖。改动这两段前先想清楚和现有手动注册的重叠。
- **`RechargeableItem`** —— 继承 `SlimefunItem implements Rechargeable`，只存一个 `capacity`（电容）。纳米套装、激光采矿器、岩石切割机都用它。
- **四个 Listener** —— 真正的游戏逻辑几乎全在事件里，且大量依赖**显示名字符串匹配**和**lore 行序解析**（见陷阱）。

## 5. 核心机制（改之前必须先懂）

### 5.1 多方块机器（MultiBlockMachine）
不是真正的方块实体，而是「用原版方块摆出特定结构 + 右键触发」：
- **工厂合成台**：`工作台(下) + 发射器(中,口朝上) + 重质压力板(上)`。
- **压板机**：`发射器(上,口朝上) + 活塞(下)`。
- **零件工厂**：`信标(底) + 发射器(中) + 轻质压力板(顶)` 且左右/前后为铁块。
右键时 `InteractListener` 读取发射器库存，按 `Setup.*Machine.getRecipes()` 匹配 3x3 配方并产出。

### 5.2 充能系统（电量/电容）
**所有电量读写都靠解析物品 lore 的字符串**，约定：
- lore 第 2 行（`lore.get(1)`）= `电量: xx.x J`
- lore 第 3 行（`lore.get(2)`）= `电容: xx.x J`
代码用 `replace("电量: ", "").replace(" J", "")...` 抠数字再 `Double.valueOf`。
涉及文件：`Commands.charge`、`BlockListener`（岩石切割机耗电）、`InteractListener`（激光耗电/切换）、`EntitiesListener`（静电靴充能、纳米套装减伤）。
> 这是全项目最脆的部分。**lore 行序或措辞一变，充能/减伤全部失效。** 改物品文案前务必同步这些解析逻辑。

### 5.3 物品识别方式
监听器判断「这是不是某件工业物品」普遍用：
```java
item.getItemMeta().getDisplayName().equalsIgnoreCase("&b激光采矿器")
```
即**直接比对带颜色码的显示名字符串**，而非 Slimefun 的 item ID。
> 改名、改颜色码都会让对应逻辑失效，且编译器不会报错。改动物品名必须全局 grep 同步。

## 6. 已知陷阱 / 红线（必读）

1. **显示名硬编码匹配**：多处用 `equalsIgnoreCase("&b激光采矿器")` 等字符串识别物品。改名即崩，且无编译期保护。
2. **lore 行序电量解析**：见 5.2，依赖第 2/3 行格式 `"电量: x J"` / `"电容: x J"`。
3. **`hasUnlocked(Player, ItemStack)` 三处重复实现**：`BlockListener` / `InteractListener` / `EntitiesListener` 各抄了一份，内部用 `PlayerProfile.find(player).get()` **阻塞式**获取玩家档案（可能卡线程）。新增逻辑时优先抽成公共方法。
4. **Research ID 冲突**：`setupResearches()` 里 `"nanosuit"`（工厂装备，85851014）和 `"tankin"`（复合材背心，85851014）**用了同一个研究 ID**。Slimefun 的 `NamespacedKey` 按 key 去重，两个研究抢同一 key 会互相覆盖，建议修成唯一 ID。
5. **Slimefun 内部 API**：`io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(...)` 这类调用跨 version 不稳。
6. **`changeRecipes`/`changeCategories` 未启用**：onEnable 里注释掉了，别误以为生效。
7. **无 null 防护**：多处 `getItemMeta().getLore()` 直接调用，玩家手持无 lore 物品时易 NPE（如 `Commands.charge` 已有 `lore.size() < 3` 检查，但部分 Listener 没有）。
8. **`MassFabricator` 是空壳**：`onInteract` 为空，`getLabelLocalPath`/`getRecipeSectionLabel` 返回 null；零件工厂的真实产出逻辑在 `InteractListener` 手写，别被类名误导去改它。

## 7. 如何新增一个工业物品（典型流程）

1. 在 `Setup` 顶部加一个 `public static final SlimefunItemStack XXX = new SlimefunItemStack("XXX", Material.X, "&b显示名", "...lore");`（**lore 若可充电，务必保留第 2/3 行的 `电量:`/`电容:` 格式**）。
2. 在 `setupItems()` 里 `new SlimefunItem(...)` 或 `new RechargeableItem(...)`，调用 `.register(main)`。
3. 若走多方块机器，用 `Setup.iwMachine.addRecipe(recipe, output)`（或 `pbMachine`/`mfMachine`）登记 3x3 配方。
4. 在 `setupResearches()` 里 `new Research(new NamespacedKey(main, "key"), <唯一ID>, "中文名", <花费>).addItems(XXX).register();` —— **ID 必须全局唯一**。
5. 若需要事件交互（耗电/特殊效果），在对应 Listener 里用**显示名匹配**写逻辑，并同步所有读取该物品 lore 的地方。
6. `mvn clean package` 验证编译，丢进服务端实测。

## 8. 常见任务速查

| 任务 | 去哪改 |
|---|---|
| 调数值（爆炸强度/耗电/掉落率） | `src/main/resources/config.yml` + 对应 Listener 读配置处 |
| 加物品/配方/研究 | `Setup.java` |
| 改命令行为（reload/charge） | `Commands.java` |
| 改多方块机器结构或合成判定 | `InteractListener.java` |
| 改储罐/液体逻辑 | `InteractListener` + `InventoryListener` + `BlockListener` |
| 改纳米套装减伤/静电靴充能 | `EntitiesListener.java` |
| 改插件元数据/权限/命令注册 | `plugin.yml` |
| 报告 bug | `https://github.com/balugaq/Slimedustry/issues/`（注意：`plugin.yml` author 写的是 j0ach1mmall3 / Diamondman121314，但 bug tracker 指向 balugaq 仓库，改动前先确认当前维护者） |

## 9. 给 AI 的硬性建议

- **不要**凭直觉改物品显示名或 lore 文案，先全局搜该字符串的所有引用。
- **不要**动 lore 第 2/3 行的 `电量:`/`电容:` 格式，除非同步改全部解析点。
- 新增充能逻辑时，复用已有的 `lore.get(1)/get(2)` 解析约定，保持统一。
- 涉及 Slimefun API 的调用，先确认 2025.1 版本是否仍存在该符号。
