package org.springframework.samples.petclinic.utility;

import static org.junit.jupiter.api.Assertions.*;


/*

By taking a glimpse into the PetTimedCache we can say that almost all the variables and fields are
somehow local and cannot be injected into the class remotely. Besides, the behavior of this class
doesn't have anything specific or worth to be verified behaviorally. The only thing that has to be done
about this class is to verify states and also their returned values. The only thing that has to be
injected into the PetTimedCache is the repository which would be easy to mock it. all facts said,
adopting the Mockist approach would be more compelling.

 */

// todo
class PetTimedCacheTest {
}
