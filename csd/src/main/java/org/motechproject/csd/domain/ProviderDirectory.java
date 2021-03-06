package org.motechproject.csd.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provider" type="{urn:ihe:iti:csd:2013}provider" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class ProviderDirectory {

    private Set<Provider> providers = new HashSet<>();

    public ProviderDirectory() {
    }

    public ProviderDirectory(Set<Provider> providers) {
        this.providers = providers;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    @XmlElement(name = "provider")
    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProviderDirectory that = (ProviderDirectory) o;

        if (providers != null ? !providers.equals(that.providers) : that.providers != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return providers != null ? providers.hashCode() : 0;
    }

    @Override
    public String toString() {
        if (providers != null && !providers.isEmpty()) {
            return providers.toString();
        }
        return  "";
    }
}
