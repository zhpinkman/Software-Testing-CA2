package org.springframework.samples.petclinic.owner;

//import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.utility.SimpleDI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class PetServiceTest {

	@Mock
	private PetTimedCache pets;
	@Mock
	private OwnerRepository owners;
	@Mock
	private Logger log;


	@InjectMocks
	private PetService petService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOwnerTest() {
		int ownerId = 20;
		given(this.owners.findById(ownerId)).willReturn(new Owner());
		petService.findOwner(ownerId);
		assertNotNull(owners);
		assertNotNull(log);
//		Mockito.verify(log, times(1)).info("find owner {}", ownerId);
		Mockito.verify(log, times(1)).info(anyString(), anyInt());
	}

	@Test
	public void newPetTest() {
		int ownerId = 20;
		Owner mockOwner = mock(Owner.class);
		given(mockOwner.getId()).willReturn(ownerId);
		assertNotNull(log);
		petService.newPet(mockOwner);
		Mockito.verify(log, times(1)).info(anyString(), anyInt());
		Mockito.verify(mockOwner, times(1)).addPet(any(Pet.class));
	}

	@Test
	public void findPetTest() {
//		todo
		int petId = 15;
		given(this.pets.get(petId)).willReturn(new Pet());
		petService.findPet(petId);
		assertNotNull(pets);
		assertNotNull(log);
		Mockito.verify(log, times(1)).info(anyString(), anyInt());
	}

	@Test
	public void savePetTest() {
//		todo
	}


}
