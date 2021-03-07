package dev.zhen.daos;

import dev.zhen.customException.ExpenseNotFoundException;
import dev.zhen.entities.Expense;
import dev.zhen.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ExpenseDaoHibernate implements ExpenseDAO{

    @Override
    public Expense createExpense(int employeeId, Expense expense) {
        expense.setEmployeeId(employeeId);
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(expense);
        session.getTransaction().commit();
        session.close();
        return expense;
    }

    @Override
    public List<Expense> getAllExpense() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "from Expense";
        Query query = session.createQuery(hql);
        List<Expense> expenseList = query.list();
        session.close();
        return expenseList;
    }

    @Override
    public List<Expense> getAllExpenseByEmployeeId(int employeeId) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "from Expense e where e.employeeId = :employeeId" ;
        Query query = session.createQuery(hql);
        query.setParameter("employeeId", employeeId);
        List<Expense> expenseList = query.list();
        session.close();
        return expenseList;
    }

    @Override
    public Expense getExpenseById(int id) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "from Expense e where e.expenseId = :expenseId" ;
        Query query = session.createQuery(hql);
        query.setParameter("expenseId", id);
        List<Expense> expenseList = query.list();
        session.close();
        if (expenseList.size() > 0)
            return expenseList.get(0);
        return null;
    }

    @Override
    public Expense updateExpenseById(int id, Expense expense) throws ExpenseNotFoundException {
        Expense expense1 = getExpenseById(id);
        if (expense1 == null) {
            throw new ExpenseNotFoundException("Expense with id " + id + " is not found");
        }
        expense.setExpenseId(id);
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(expense);
        session.getTransaction().commit();
        session.close();
        return expense;
    }

    @Override
    public boolean deleteExpenseById(int id) throws ExpenseNotFoundException {
        Expense expense = getExpenseById(id);
        if (expense == null) {
            throw new ExpenseNotFoundException("Expense with id " + id + " is not found");
        } else {
            try {
                SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.delete(expense);
                session.getTransaction().commit();
                session.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
