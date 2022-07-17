package net.timenation.timevelocityapi.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

/**
 * Created by EinsLuca
 * Class create at 17.07.2022 19:02 @TimeVelocityAPI
 **/
public class TimeConfig {
    @Getter
    private MySQLConfigObject sqlCredentials;

    @Getter
    private RabbitMQConfigObject rabbitMQCredentials;

    public static TimeConfig loadConfig(File file) {
        if(!file.exists()) {
            TimeConfig timeConfig = new TimeConfig();
            timeConfig.sqlCredentials = new MySQLConfigObject("admin", "test", "timenation", "127.0.0.1");
            timeConfig.rabbitMQCredentials = new RabbitMQConfigObject("admin", "127.0.0.1", "127.0.0.1");

            try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                fileOutputStream.write(gson.toJson(timeConfig).getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return timeConfig;
        }

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            return new Gson().fromJson(new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8), TimeConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
