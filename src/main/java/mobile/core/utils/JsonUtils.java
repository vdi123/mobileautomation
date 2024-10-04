package mobile.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import mobile.core.business.businessObjects.DeviceData;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JsonUtils {

    public static <T> T getData(String testData, String nodeKey, Class<T> clazz) throws IOException, URISyntaxException {
        String jsonString = readFileToString(testData);
        JsonElement jelement = new JsonParser().parse(jsonString);
        return new Gson().fromJson(jelement.getAsJsonObject().get(nodeKey), clazz);
    }

    public static DeviceData getDeviceData(String mobileDevice) throws IOException, URISyntaxException {
        return getData(Paths.TestData.DEVICE_DATA, mobileDevice, DeviceData.class);
    }

    public static String readFileToString(String path) throws IOException, URISyntaxException {
        URL resource = JsonUtils.class.getResource(path);
        File file = java.nio.file.Paths.get(resource.toURI()).toFile();
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

}
