# TQLSlib

作者tqls的一个前置Mod

## 模组简介

TQLSlib 是一个 Mindustry 前置库模组，为其他模组提供丰富的功能支持。本模组包含完整的空间站系统、数据持久化、UI 对话框框架等核心功能，便于开发者快速构建复杂的模组功能。

## 功能特性

### 空间站系统
- **一星球多空间站**：支持在单个星球上创建多个独立的空间站
- **空间站管理**：支持创建、删除、重命名空间站
- **数据持久化**：自动保存和加载空间站数据
- **UI 对话框框架**：提供预设的对话框用于空间站管理

### 核心模块
- **SpaceStationManager**：空间站核心管理器，提供所有空间站操作的静态方法
- **SpaceStationDialogs**：UI 对话框框架，包含管理器、选择、创建、删除确认、重命名等对话框
- **SpaceStationIO**：数据持久化系统，负责空间站数据的读写
- **DTVars**：全局变量管理，维护空间站信息和映射关系

## 开发者使用指南

### 依赖配置

在您的模组中添加 TQLSlib 作为依赖项：

```gradle
// 在 build.gradle 中添加
dependencies {
    compileOnly project(':tqlslib')
}
```

### 空间站系统使用示例

#### 创建空间站
```java
import mindustry.type.Planet;
import tqlslib.core.SpaceStationManager;
import tqlslib.type.SpaceStation;

// 获取目标星球
Planet parentPlanet = Vars.content.planet("serpulo");

// 创建空间站
SpaceStation station = SpaceStationManager.createStation(parentPlanet, "我的空间站");
if (station != null) {
    Log.info("空间站创建成功，ID: " + station.stationId);
}
```

#### 使用对话框
```java
import tqlslib.ui.SpaceStationDialogs;
import tqlslib.type.SpaceStation;

// 显示空间站选择对话框
SpaceStationDialogs.showSelectDialog(parentPlanet, station -> {
    // 用户选择空间站后的回调
    Log.info("选择了空间站: " + station.localizedName);
});

// 显示空间站管理器
SpaceStationDialogs.showManagerDialog();
```

#### 核心管理方法
```java
import tqlslib.core.SpaceStationManager;
import tqlslib.type.SpaceStation;
import arc.struct.Seq;

// 根据星球获取所有空间站
Seq<SpaceStationInfo> stations = SpaceStationManager.getStationsByPlanet(parentPlanet);

// 根据ID获取空间站
SpaceStation station = SpaceStationManager.getStationById(stationId);

// 重命名空间站
SpaceStationManager.renameStation(stationId, "新名称");

// 删除空间站
SpaceStationManager.removeStation(stationId);

// 检查空间站是否存在
boolean exists = SpaceStationManager.stationExists(stationId);

// 获取下一个空间站编号
int nextNumber = SpaceStationManager.getNextStationNumber(parentPlanet);
```

## 核心类参考

### SpaceStationManager
位于 `tqlslib.core.SpaceStationManager`

| 方法 | 描述 |
|------|------|
| `createStation(Planet, String)` | 创建新空间站 |
| `removeStation(String)` | 删除指定ID的空间站 |
| `renameStation(String, String)` | 重命名空间站 |
| `getStationsByPlanet(Planet)` | 获取指定星球的所有空间站 |
| `getStationById(String)` | 根据ID获取空间站 |
| `getNextStationNumber(Planet)` | 获取下一个空间站编号 |
| `stationExists(String)` | 检查空间站是否存在 |
| `getAllStations()` | 获取所有空间站 |
| `getStationCount()` | 获取空间站总数 |
| `getStationCountByPlanet(Planet)` | 获取指定星球的空间站数量 |

### SpaceStationDialogs
位于 `tqlslib.ui.SpaceStationDialogs`

| 方法 | 描述 |
|------|------|
| `showManagerDialog()` | 显示空间站管理器 |
| `showSelectDialog(Planet, StationSelectCallback)` | 显示空间站选择对话框 |
| `showCreateDialog(Planet, StationCreateCallback)` | 显示空间站创建对话框 |
| `showDeleteConfirmDialog(SpaceStation, Runnable)` | 显示删除确认对话框 |
| `showRenameDialog(SpaceStation, NameChangeCallback)` | 显示重命名对话框 |

### 回调接口
- `StationSelectCallback`：空间站选择回调，包含 `onSelect(SpaceStation)` 方法
- `StationCreateCallback`：空间站创建回调，包含 `onCreate(SpaceStation)` 方法
- `NameChangeCallback`：名称变更回调，包含 `onRename(String)` 方法

# Mindustry Java 模组模板
一个适用于 Android 和 PC 的 Mindustry Java 模组模板。此模组的 Kotlin 版本可在此处查看 [here](https://github.com/Anuken/MindustryKotlinModTemplate)。

## 构建桌面测试版本

1. 安装 JDK **17**。
2. 运行 `gradlew jar` [1]。
3. 模组 jar 文件将位于 `build/libs` 目录中。**仅将此版本用于桌面测试。它不适用于 Android。**
要构建兼容 Android 的版本，您需要 Android SDK。您可以让 Github Actions 处理此问题，或自行设置。请参阅下面的步骤。


## 通过 Github Actions 构建

此仓库已设置 Github Actions CI，可在每次提交时自动为您构建模组。显然，这需要一个 Github 仓库。
要获取适用于所有平台的 jar 文件，请执行以下操作：
1. 使用您的模组名称创建一个 Github 仓库，并将此仓库的内容上传到其中。进行必要的修改，然后提交并推送。
2. 检查您仓库页面上的 "Actions" 选项卡。选择列表中最新的提交。如果完成成功，"Artifacts" 部分下应该有一个下载链接。
3. 点击下载链接（应该是您的仓库名称）。这将下载一个 **压缩的 jar** - **不是** jar 文件本身 [2]！解压此文件并在 Mindustry 中导入其中包含的 jar。此版本应该可在 Android 和 Desktop 上运行。

## 本地构建

本地构建需要更多时间设置，但如果您以前做过 Android 开发，应该不会有问题。
1. 下载 Android SDK，解压并将 `ANDROID_HOME` 环境变量设置为其位置。
2. 确保您已安装 API 级别 30 以及任何最新版本的构建工具（例如 30.0.1）
3. 将 build-tools 文件夹添加到您的 PATH 中。对于 tqlslib，如果您安装了 `30.0.1`，则路径为 `$ANDROID_HOME/build-tools/30.0.1`。
4. 运行 `gradlew deploy`。如果您操作正确，这将在 `build/libs` 目录中创建一个可在 Android 和桌面端运行的 jar 文件。

## 添加依赖项

请注意，所有对 Mindustry、Arc 或其子模块的依赖项 **必须在 Gradle 中声明为 compileOnly**。切勿对核心 Mindustry 或 Arc 依赖项使用 `implementation`。

- `implementation` **会将整个依赖项放入 jar 中**，这在大多数模组依赖项中是非常不可取的。您不希望将整个 Mindustry API 包含在您的模组中。
- `compileOnly` 意味着依赖项仅在编译时存在，不包含在 jar 中。

只有当您想在模组中打包另一个 Java 库，并且该库尚未在 Mindustry 中存在时，才使用 `implementation`。

--- 

*[1]* *在 Linux/Mac 上是 `./gradlew`，但如果您使用的是 Linux，我假设您知道如何正确运行可执行文件。*  
*[2]: 是的，我知道这很愚蠢。这是 Github UI 的限制 - 虽然 jar 文件本身是未经压缩上传的，但目前无法将其作为单个文件下载。*
