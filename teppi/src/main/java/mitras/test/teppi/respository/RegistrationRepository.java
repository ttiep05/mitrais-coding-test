package mitras.test.teppi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mitras.test.teppi.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	@Query(value = "SELECT r FROM Registration r WHERE r.phoneNumber = ?1")
	Registration findByPhoneNumber(String phoneNumber);

	@Query(value = "SELECT r FROM Registration r WHERE r.email = ?1")
	Registration findByEmail(String email);
}
