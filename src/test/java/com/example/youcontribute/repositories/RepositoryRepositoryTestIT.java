package com.example.youcontribute.repositories;

import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.example.youcontribute.models.Repository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(initializers = RepositoryRepositoryTestIT.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@Testcontainers
public class RepositoryRepositoryTestIT {

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7");

    @Container
    public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>(MYSQL_57_IMAGE);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Test
    public void it_should_find_all_repositories() {
        // Given
        Repository repository1 = Repository.builder().repository("repo1").organization("org1").build();
        Repository repository2 = Repository.builder().repository("repo2").organization("org2").build();

        this.repositoryRepository.save(repository1);
        this.repositoryRepository.save(repository2);
        testEntityManager.flush();

        // When
        List<Repository> repos = this.repositoryRepository.findAll();

        // Then
        BDDAssertions.then(repos.size()).isEqualTo(2);
        Repository repo1 = repos.get(0);
        BDDAssertions.then(repo1.getRepository()).isEqualTo("repo1");
        BDDAssertions.then(repo1.getOrganization()).isEqualTo("org1");

        Repository repo2 = repos.get(1);
        BDDAssertions.then(repo2.getRepository()).isEqualTo("repo2");
        BDDAssertions.then(repo2.getOrganization()).isEqualTo("org2");
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + mysqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mysqlContainer.getUsername(),
                    "spring.datasource.password=" + mysqlContainer.getPassword())
                    .applyTo(applicationContext.getEnvironment());
        }
    }
}
