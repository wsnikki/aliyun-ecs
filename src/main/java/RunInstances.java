import com.aliyun.ecs20140526.Client;
import com.aliyun.ecs20140526.models.RunInstancesRequest;
import com.aliyun.ecs20140526.models.RunInstancesRequest.RunInstancesRequestSystemDisk;
import com.aliyun.ecs20140526.models.RunInstancesResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.google.gson.Gson;

public class RunInstances {
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
        Client client = RunInstances.createClient();
        RunInstancesRequestSystemDisk systemDisk = new RunInstancesRequestSystemDisk().setCategory("cloud_essd");
        RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
                .setRegionId("cn-beijing")
                .setInstanceType("ecs.g7.large")
                .setImageId("aliyun_3_x64_20G_scc_alibase_20220225.vhd")
                .setSecurityGroupId("sg-2ze5ue75rqizzao3m5qa")
                .setInstanceName("ecs_sdk_demo_nikki")
                .setVSwitchId(
                        "vsw-2ze4ine594mkygx80mhq0")
                .setInstanceChargeType("PostPaid")
                .setInternetMaxBandwidthOut(1)
                .setSystemDisk(systemDisk)
                .setPassword("123Whfdfa!~");
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            RunInstancesResponse resp = client.runInstancesWithOptions(runInstancesRequest, runtime);
            System.out.println(new Gson().toJson(resp.getBody()));
        } catch (Exception error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
        }
    }
}