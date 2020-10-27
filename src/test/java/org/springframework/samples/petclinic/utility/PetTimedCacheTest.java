package org.springframework.samples.petclinic.utility;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class PetTimedCacheTest {

	@Mock
	private PetRepository petRepository;

	private PetTimedCache petTimedCache;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		petTimedCache = new PetTimedCache(petRepository);
	}

	@Test
	public void initTest() {
		assertNotNull(petTimedCache);
		assertNotNull(petRepository);
	}

	@Test
	public void saveTest() {
		Pet petInstance = new Pet();
		petTimedCache.save(petInstance);
		Mockito.verify(petRepository, Mockito.times(1)).save(petInstance);
	}

	@Test
	public void getTest() {
		int key = 20;
		Pet petInstance = mock(Pet.class);
		given(petInstance.getId()).willReturn(key);
		given(petRepository.findById(key)).willReturn(petInstance);
		Pet returnedPet = petTimedCache.get(key);
		verify(petInstance, times(1)).getId();
		verify(petRepository, times(1)).findById(key);
		assertEquals(returnedPet, petInstance);
	}

}
