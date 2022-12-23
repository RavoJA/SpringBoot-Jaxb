package jar.dev.jaxb;

import jakarta.xml.bind.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class JaxbApplication {

    public static void main(String[] args) throws JAXBException, IOException {
        SpringApplication.run(JaxbApplication.class, args);

        JAXBContext jaxbContext = JAXBContext.newInstance(Personnes.class);
        Personnes personnes = Personnes.builder()
                .age(32)
                .name("Jean Aimé")
                .birthDate(new Date())
                .companies(Arrays.asList(Company.builder().companyType(CompanyType.EI).name("Test Company").raisonSocial("Info")
                        .build(), Company.builder().companyType(CompanyType.SARL).raisonSocial("Prestation").name("test 2").build()))
                .address(Address.builder().country("Madagascar").postCode("101").zipCode("35").build())
                .build();
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(personnes, new File("Personnes.xml"));

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Personnes personnes1 = (Personnes) unmarshaller.unmarshal(new File("Personnes.xml"));
        log.info("Personnes =============== {} ", personnes1);

        jaxbContext.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String s, String s1) {
                return new StreamResult(new File("Personnes.xsd"));
            }
        });
    }

}
