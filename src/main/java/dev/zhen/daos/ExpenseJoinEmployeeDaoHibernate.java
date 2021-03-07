package dev.zhen.daos;

import dev.zhen.entities.Employee;
import dev.zhen.entities.Expense;
import dev.zhen.entities.ExpenseJoinEmployee;
import dev.zhen.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class ExpenseJoinEmployeeDaoHibernate implements ExpenseJoinEmployeeDAO{

    @Override
    public List<ExpenseJoinEmployee> getAllExpenseJoinEmployee() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "from Employee";
        Query query = session.createQuery(hql);
        List<Employee> employeeList = query.list();
        List<ExpenseJoinEmployee> expenseJoinEmployeeList = new LinkedList<>();
        if(employeeList != null) {
            for (Employee employee : employeeList) {
                List<Expense> expenseList = employee.getExpenseList();
                for (Expense expense : expenseList) {
                    expenseJoinEmployeeList.add(new ExpenseJoinEmployee(expense, employee));
                }
            }
        }
        session.close();
        if (expenseJoinEmployeeList.size() > 0)
            return expenseJoinEmployeeList;
        return null;
    }
}
