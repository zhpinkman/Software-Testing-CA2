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
		Owner ownerInstance = new Owner();
		given(this.owners.findById(ownerId)).willReturn(ownerInstance);
		Owner returnedOwner = petService.findOwner(ownerId);
		assertNotNull(this.owners);
		assertNotNull(this.log);
		Mockito.verify(this.log, times(1)).info(anyString(), anyInt());
		assertEquals(returnedOwner, ownerInstance);
	}

	@Test
	public void newPetTest() {
		int ownerId = 20;
		Owner mockOwner = mock(Owner.class);
		given(mockOwner.getId()).willReturn(ownerId);
		assertNotNull(this.log);
		Pet returnedPet = petService.newPet(mockOwner);
		Mockito.verify(this.log, times(1)).info(anyString(), anyInt());
		Mockito.verify(mockOwner, times(1)).addPet(any(Pet.class));
		assertNotNull(returnedPet);
	}

	@Test
	public void findPetTest() {
		int petId = 15;
		Pet petInstance = new Pet();
		given(this.pets.get(petId)).willReturn(petInstance);
		Pet returnedPet = petService.findPet(petId);
		assertNotNull(this.pets);
		assertNotNull(this.log);
		Mockito.verify(this.log, times(1)).info(anyString(), anyInt());
		Mockito.verify(this.pets, times(1)).get(petId);
		assertEquals(returnedPet, petInstance);
	}

	@Test
	public void savePetTest() {
		int petId = 30;
		Owner ownerMock = mock(Owner.class);
		Pet petMock = mock(Pet.class);
		given(petMock.getId()).willReturn(petId);
		petService.savePet(petMock, ownerMock);
		assertNotNull(this.log);
		Mockito.verify(ownerMock, times(1)).addPet(petMock);
		Mockito.verify(this.log, times(1)).info(anyString(), anyInt());
		Mockito.verify(this.pets, times(1)).save(petMock);
	}


}
