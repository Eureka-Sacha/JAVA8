package org.eureka.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

/**
 * @author: 奎
 * @date: 2017/9/17 16:07
 * @description:
 */
public class DockerForJavaDemo {
    public static void main(String[] args) {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://192.168.31.57:2375")
//                .withDockerTlsVerify(true)
//                .withDockerCertPath("/home/user/.docker/certs")
//                .withDockerConfig("/home/user/.docker")
//                .withApiVersion("1.23")
//                .withRegistryUrl("https://index.docker.io/v1/")
//                .withRegistryUsername("root")
//                .withRegistryPassword("1234")
//                .withRegistryEmail("eureka_sacha@outlook.com")
                .build();
        DockerClient docker = DockerClientBuilder.getInstance(config).build();
        Info str=docker.infoCmd().exec();
        System.out.println(str.getContainers());
    }
}
