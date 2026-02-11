# TQLSlib - Mindustry Java 模组模板
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
