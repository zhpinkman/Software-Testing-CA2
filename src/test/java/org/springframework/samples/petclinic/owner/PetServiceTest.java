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

/*

Our approach toward adopting a Classical or Mockisty one.
To answer this, it's better to refer to this statement of martin fowler website:
"If it's an easy collaboration then the choice is simple.
If I'm a classic TDDer I don't use a mock, stub or any kind of double.
I use a real object and state verification.
If I'm a mockist TDDer I use a mock and behavior verification. No decisions at all."
Having this in mind and also the fact that we want to prevent usage of any other class but the PetService,
it sounds reasonable that we adopted the latter approach. By doing this we're able to just monitor behavior that each object is showing from itself,
and also verify it. On this ground, all of our test are using mocks, and verify tests by their behavior.
This behavior is also referred to as BDD that stands for Behavior Driven Development.




verification methods:*Each test that contains -assert equal- has some sort of state verification.
					 *If we set some object for a class that would also be state verification,
						For example when we set pet or save pet in the last two test.
					 *Each test also has some behavior verification part. By using the "verify"
						method we will -observe- whether a specific method has been called in the
						corresponding Mock object. -Verify log- can be found inside every test.
						by verifying log we determine that log method has been called and furthermore
						if it consists of string and int.

*/

class PetServiceTest {

//	this mock is used as both getting return values just a as stub and also for behavior verification
	@Mock
	private PetTimedCache pets;

//	this double is used as a stub to just return some sound value when called
	@Mock
	private OwnerRepository owners;

//	this mock is used for behavior verification in all the tests
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
//		dummy object
		Owner ownerInstance = new Owner();
		given(this.owners.findById(ownerId)).willReturn(ownerInstance);
		assertNotNull(this.owners);
		assertNotNull(this.log);
		Owner returnedOwner = petService.findOwner(ownerId);
		Mockito.verify(this.log, times(1)).info("find owner {}", ownerId);
		assertEquals(returnedOwner, ownerInstance);
	}


	//Here the addPet method has been checked by mockito.verify. An example of behavior verification.
	@Test
	public void newPetTest() {
		int ownerId = 20;
		Owner mockOwner = mock(Owner.class);
		given(mockOwner.getId()).willReturn(ownerId);
		assertNotNull(this.log);
		Pet returnedPet = petService.newPet(mockOwner);
		Mockito.verify(this.log, times(1)).info("add pet for owner {}", ownerId);
		Mockito.verify(mockOwner, times(1)).addPet(any(Pet.class));
		Mockito.verify(mockOwner, times(1)).getId();
		assertNotNull(returnedPet);
	}


	//Here the get method has been checked by mockito.verify. An example of behavior verification.
	@Test
	public void findPetTest() {
		int petId = 15;
//		dummy object
		Pet petInstance = new Pet();
		given(this.pets.get(petId)).willReturn(petInstance);
		assertNotNull(this.pets);
		assertNotNull(this.log);
		Pet returnedPet = petService.findPet(petId);
		Mockito.verify(this.log, times(1)).info("find pet by id {}", petId);
		Mockito.verify(this.pets, times(1)).get(petId);
		assertEquals(returnedPet, petInstance);
	}


	//Here the addPet & save methods have been checked by mockito.verify. An example of behavior verification.
	@Test
	public void savePetTest() {
		int petId = 30;
//		mock object
		Owner ownerMock = mock(Owner.class);
//		for both stubbing and verifying the behavior
		Pet petMock = mock(Pet.class);
		given(petMock.getId()).willReturn(petId);
		petService.savePet(petMock, ownerMock);
		assertNotNull(this.log);
		Mockito.verify(ownerMock, times(1)).addPet(petMock);
		Mockito.verify(this.log, times(1)).info("save pet {}", petId);
		Mockito.verify(this.pets, times(1)).save(petMock);
		Mockito.verify(petMock, times(1)).getId();
	}


}
