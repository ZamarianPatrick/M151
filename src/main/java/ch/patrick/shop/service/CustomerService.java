package ch.patrick.shop.service;

import ch.patrick.shop.model.Address;
import ch.patrick.shop.model.City;
import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.CustomerType;
import ch.patrick.shop.repository.AddressRepository;
import ch.patrick.shop.repository.CityRepository;
import ch.patrick.shop.repository.CustomerRepository;
import ch.patrick.shop.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service für Benutzer
 *
 * @author Patrick
 * @version 1.0
 */
@Service
public class CustomerService {

    /**
     * Repository für Benutzer
     */

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Repository für Orte
     */

    @Autowired
    private CityRepository cityRepository;

    /**
     * Repository für Adressen
     */

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Fügt ein Benutzer hinzu
     *
     * @param customer Benutzer welcher hinzugefügt werden soll
     * @return id des neuen Benutzers
     */

    public Long addCustomer(Customer customer){
        validateAddress(customer);
        customer.setPassword(Encryptor.hashPassword(customer.getPassword()).get());
        if(customer.getCustomerType() == null){
            customer.setCustomerType(CustomerType.USER);
        }
        customerRepository.saveAndFlush(customer);
        return customer.getId();
    }

    /**
     * Speichert ein Benutzer
     *
     * @param customer zu speichernder Benutzer
     */

    public void updateCustomer(Customer customer){
        validateAddress(customer);
        customerRepository.saveAndFlush(customer);
    }

    /**
     * Liefert ein Benutzer über den Username
     * @param username Benutzername des Benutzers
     * @return Benutzer
     */

    public Optional<Customer> getByUsername(String username){
        return customerRepository.findByUsername(username);
    }

    /**
     * Liefert ein Benutzer über die Email
     * @param email Email des Benutzers
     * @return Benutzer
     */

    public Optional<Customer> getByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    /**
     * Liefert ein Benutzer über die ID
     * @param id id des Benutzers
     * @return Bentzer
     */

    public Optional<Customer> getById(Long id){ return customerRepository.findById(id); }

    /**
     * Lierfert eine Liste aller Benutzer
     *
     * @return Liste aller Benutzer
     */

    public List<Customer> getAll(){return customerRepository.findAll();}

    /**
     * Validiert die Adresse des Benutzers
     *
     * @param customer Benutzer
     */

    private void validateAddress(Customer customer){
        Optional<City> optionalCity = cityRepository.findByNameAndZip(customer.getAddress().getCity().getName(), customer.getAddress().getCity().getZip());
        Optional<Address> optionalAddress;

        if(optionalCity.isPresent()){
            optionalAddress = addressRepository.findByStreetAndCity(customer.getAddress().getStreet(), optionalCity.get());
            customer.getAddress().setCity(optionalCity.get());
            if(optionalAddress.isPresent()){
                customer.setAddress(optionalAddress.get());
            }
        }
    }
}
