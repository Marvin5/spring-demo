package com.example.demo.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		Customer c = new Customer(null, "marvin", "123");
		entityManager.persist(c);
	}

	@Test
	public void testSave() {
		Customer c = customerRepository.findByUsername("marvin");
		assertEquals("123", c.getPassword());
	}

}
