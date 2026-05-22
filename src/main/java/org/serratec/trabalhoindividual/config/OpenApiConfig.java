package org.serratec.trabalhoindividual.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private String versao;

    public OpenApiConfig(@Value("${api.versao}") String versao) {
        this.versao = versao;
    }

    @Bean
    public OpenAPI config() {
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("API de Gestão de clientes e veiculos (local)");

        Contact contact = new Contact();
        contact.setName("Guilherme Fernandes Guingo");

        Info info = new Info()
                .contact(contact)
                .description("API de Gestão de clientes e veiculos")
                .title("Trabalho Individual Concessionária")
                .version(this.versao);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}