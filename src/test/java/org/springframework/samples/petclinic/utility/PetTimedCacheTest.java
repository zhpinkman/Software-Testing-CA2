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

import static org.junit.jupiter.api.Assertions.*;



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

}
