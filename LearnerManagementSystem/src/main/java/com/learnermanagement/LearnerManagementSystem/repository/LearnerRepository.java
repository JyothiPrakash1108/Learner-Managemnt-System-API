<<<<<<< HEAD
package com.learnermanagement.LearnerManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long>{
    Learner findByName(String name);
    Optional<Learner> findByEmail(String email);
}
=======
package com.learnermanagement.LearnerManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnermanagement.LearnerManagementSystem.entity.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long>{
    Learner findByName(String name);
    Optional<Learner> findByEmail(String email);
}
>>>>>>> fe99840de0a1cc86501a349676a2aff64b35115a
