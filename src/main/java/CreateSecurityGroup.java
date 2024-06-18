import com.aliyun.ecs20140526.Client;
import com.aliyun.ecs20140526.models.CreateSecurityGroupRequest;
import com.aliyun.ecs20140526.models.CreateSecurityGroupResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.google.gson.Gson;

public class CreateSecurityGroup {
    /**
     * 使用AK&SK初始化账号Client
     * 本示例通过从环境变量中读取AccessKey，来实现API访问的身份验证。请根据你的生产环境要求适当调整。
     * 避免AK&SK等关键信息在代码中明文存储是云上安全红线！
     * 
     * @return Client
     * @throws Exception
     */
    public static Client createClient() throws Exception {
        Config config = new Config()
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        config.endpoint = "ecs-cn-hangzhou.aliyuncs.com";
        return new Client(config);
    }

    public static void main(String[] args) throws Exception {
        Client client = CreateSecurityGroup.createClient();
        CreateSecurityGroupRequest createSecurityGroupRequest = new CreateSecurityGroupRequest()
                .setRegionId("cn-hangzhou")
                .setVpcId("vpc-bp1ua1jy0dwui0yfyw4mn");
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            CreateSecurityGroupResponse resp = client.createSecurityGroupWithOptions(createSecurityGroupRequest,
                    runtime);
            System.out.println(new Gson().toJson(resp.getBody()));
        } catch (Exception error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
        }
    }
}