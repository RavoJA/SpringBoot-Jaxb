package jar.dev.jaxb;

import lombok.Getter;

@Getter
public enum CompanyType {
    EI("EI"), SA("SA"), SARL("SARL"), SARLU("SARLU");

    private final String type;

    CompanyType(String type) {
        this.type = type;
    }
}
