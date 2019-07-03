package com.sergeev.finance.service;

import com.sergeev.finance.domain.Transaction;
import com.sergeev.finance.domain.User;
import com.sergeev.finance.repos.TransactionRepo;
import org.hibernate.*;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.*;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public Object findAll() {
        return transactionRepo.findAll();
    }

    public Transaction findTransactionById (Long id){
        return transactionRepo.findAllById(id).get(0);
    }



//    Session session = sessionFactory.getCurrentSession();
//
//    protected Session getSession(){
//        return sessionFactory.getCurrentSession();
//    }
//
//    public static HttpSession session() {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        return attr.getRequest().getSession(true); // true == allow create
//    }

//    public List<Transaction> find(User user){
//        List<Transaction> transactions = new ArrayList<>();
//        List<Object[]> listQuery;
//
//        String sql = "SELECT FROM transactions AS s where s.user_id = :userId";
//        List result = session().createSQLQuery(sql)
//                .setParameter("userId", user.getId())
//                .list();
//
//        //listQuery = result;
//
//        for (Object o : result) {
//            System.out.println(o);
//        }
//
//
//        return null;
//    }





}
