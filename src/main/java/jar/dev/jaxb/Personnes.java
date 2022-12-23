package jar.dev.jaxb;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Personnes {

    private String name;

    private Address address;

    private int age;

    private Date birthDate;

    @XmlElement(name = "company")
    @XmlElementWrapper(name = "companies")
    private List<Company> companies = new ArrayList<>();
}
