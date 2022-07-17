package net.timenation.timevelocityapi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EinsLuca
 * Class create at 17.07.2022 21:29 @TimeVelocityAPI
 **/

@AllArgsConstructor
@Getter
@Setter
public class RabbitMQConfigObject {

    public String user;
    public String password;
    public String host;

}
