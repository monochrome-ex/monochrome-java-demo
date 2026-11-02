
## Monochrome API 文档与配置

- 生产对外文档：https://doc.monochrome.exchange/
- 测试对外文档：https://doc.monochromedev.click/
- 当前 Demo 接口域名默认使用测试环境。
- 使用 JDK 21，编译：`./gradlew compileJava compileTestJava`。
- 通过环境变量 `MONOCHROME_API_KEY`、`MONOCHROME_SECRET_KEY` 配置自己的凭据；可选 `MONOCHROME_ACCESS_TOKEN`。OAuth 示例使用 `MONOCHROME_OAUTH_CLIENT_ID`、`MONOCHROME_OAUTH_CLIENT_SECRET`、`MONOCHROME_OAUTH_CODE`、`MONOCHROME_OAUTH_REFRESH_TOKEN`。
- 测试包含下单、撤单、划转等真实业务操作，请逐个检查参数并选择执行，勿直接运行全量测试。不要提交凭据。
