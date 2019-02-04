package spring.test;

import org.hamcrest.Matchers;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.*;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringJUnit4ClassRunner.class)
public class SomeBeanIntegrationTest extends BaseIntegrationSpec {

    @Autowired
    SomeBean someBean;

    @PersistenceContext
    EntityManager em;

    @PersistenceUnit
    EntityManagerFactory emf;


//    @PersistenceContext
//    Session session;
//
//    @PersistenceUnit
//    SessionFactory sessionFactory;

    @Test
    public void contextUp() {
        System.out.println(someBean.someBeanFullName);

        assertThat(em, not(nullValue()));
        assertThat(emf, not(nullValue()));


        /**
         * Some comment say, that it need a lot boilerplate code ...
         * But it can be achieved somehow
         */
        Session session = (org.hibernate.Session) em.getDelegate();
        SessionFactory sessionFactory = session.getSessionFactory();

        assertThat(session, not(nullValue()));
        assertThat(sessionFactory, not(nullValue()));

        //This is only to play with native hibernate
        String sql = "SELECT count(*) FROM APP_ROLE";
        NativeQuery nativeQuery = session.createNativeQuery(sql);
        int singleResult = ((BigInteger) nativeQuery.getSingleResult()).intValue();
        assertThat(singleResult, Matchers.is(12));
    }
}