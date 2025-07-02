package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Address;
import com.example.bilabonnement.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


/**
 * Repository-lag for Customer-objekter.
 *
 * Denne klasse håndterer al databaseadgang for kunder og tilhørende adresser
 * ved hjælp af JdbcTemplate. Den tilbyder metoder til at hente, søge, oprette
 * og hente den sidst oprettede kunde, samt indsætte adresser med genererede
 * nøgler.
 */
@Repository
public class CustomerRepo {

    private final JdbcTemplate template;

    /**
     * Custom mapper der bygger et Customer-objekt inkl. det tilhørende Address-objekt
     * baseret på de kolonner, der returneres fra SQL-forespørgslen.
     */
    private final RowMapper<Customer> customerWithAddressMapper = (rs, rowNum) -> {
        Address address = new Address();
        address.setAddressId(rs.getInt("address_id"));
        address.setCountry(rs.getString("country"));
        address.setCity(rs.getString("city"));
        address.setZip(rs.getString("zip"));
        address.setStreet(rs.getString("street"));

        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setCustomerName(rs.getString("customer_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPhoneNumber(rs.getString("phone_number"));
        customer.setCprNumber(rs.getString("cpr_number"));
        customer.setActive(rs.getBoolean("is_active"));
        customer.setAddress(address);

        return customer;
    };


    /**
     * Konstruktor med dependency injection af JdbcTemplate.
     * Spring sørger for at injicere en konfigureret JdbcTemplate-instans.
     */
    @Autowired
    public CustomerRepo(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Hent alle kunder med tilhørende adresse via en JOIN mellem customer og address-tabellerne.
     * @return Liste af Customer-objekter med indlejret Address
     */
    public List<Customer> findAllWithAddress() {
        String sql = """
            SELECT
              c.customer_id,
              c.customer_name,
              c.email,
              c.phone_number,
              c.cpr_number,
              c.is_active,
              a.address_id,
              a.country,
              a.city,
              a.zip,
              a.street
            FROM customer c
            JOIN address a ON c.address_id = a.address_id
            """;
        // template.query eksekverer SQL og mapper hver række vha. customerWithAddressMapper
        return template.query(sql, customerWithAddressMapper);
    }

    /**
     * Søg efter kunder på navn, e-mail eller telefonnummer (case-insensitive).
     *
     * @param keyword Den fritekst, der søges efter. Værdien konverteres til små bogstaver
     *                og omgives automatisk af procent-tegn (wildcards), så en søgning
     *                på fx "ped" også matcher både "Peder", "peder@example.com" og
     *                "+45 12 34 56 78".
     * @return Liste af Customer-objekter, hvis navn, e-mail eller telefonnummer
     *         indeholder søgetermen uanset store/små bogstaver.
     */
    public List<Customer> searchWithAddress(String keyword) {
        String sql = """
        SELECT
          c.customer_id,
          c.customer_name,
          c.email,
          c.phone_number,
          c.cpr_number,
          c.is_active,
          a.address_id,
          a.country,
          a.city,
          a.zip,
          a.street
        FROM customer c
        JOIN address a ON c.address_id = a.address_id
        WHERE LOWER(c.customer_name) LIKE ?
           OR LOWER(c.email)         LIKE ?
           OR LOWER(c.phone_number)  LIKE ?
        """;
        // Byg søgeterm med wildcards og lowercase for case-insensitive match. % = wildcard.
        String term = "%" + keyword.toLowerCase() + "%";
        return template.query(
                sql,
                new Object[]{ term, term, term },
                customerWithAddressMapper
        );
    }

    /**
     * Tilføj en ny adresse til address-tabellen og sæt den genererede ID på Address-objektet.
     * @param address Address-objekt med street, city, zip og country udfyldt
     */
    public void addAddress(Address address) {
        String sql = "INSERT INTO address (street, city, zip, country) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // Benyt en PreparedStatementCreator for at få fat i det auto-genererede primærnøgle
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getZip());
            ps.setString(4, address.getCountry());
            return ps;
        }, keyHolder);
        // Sæt den genererede nøgle på Address-objektet
        address.setAddressId(keyHolder.getKey().intValue());
    }

    /**
     * Tilføj en ny kunde til customer-tabellen.
     * @param customer Customer-objekt med alle felter udfyldt inkl. reference til Address
     */
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_name, email, phone_number, cpr_number, address_id, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        // Eksekver INSERT med kundens egenskaber
        template.update(sql,
                customer.getCustomerName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCprNumber(),
                customer.getAddress().getAddressId(),
                customer.isActive());
    }

    /**
     * Hent den sidst oprettede kunde baseret på created_date-felt (antaget tilstede).
     * @return Det sidste Customer-objekt med tilhørende Address
     */
    public Customer getLastCreatedCustomer() {
        String sql = """
        SELECT
          c.customer_id,
          c.customer_name,
          c.email,
          c.phone_number,
          c.cpr_number,
          c.is_active,
          a.address_id,
          a.country,
          a.city,
          a.zip,
          a.street
        FROM customer c
        JOIN address a ON c.address_id = a.address_id
        ORDER BY c.created_date DESC
        LIMIT 1
        """;
        return template.queryForObject(sql, customerWithAddressMapper);
    }

}
