package com.votifysoft.app.dao;

import org.apache.commons.lang3.StringUtils;

import com.votifysoft.model.entity.User;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.*;

public class GenericDao<T> implements GenericDaoI<T> {

    private EntityManager em;

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> list(T entity) {
        Class<?> clazz = entity.getClass();

        String simpleName = entity.getClass().getSimpleName();

        String tAlias = (simpleName.charAt(0) + "_").toLowerCase();
        String jpql = "FROM " + entity.getClass().getSimpleName() + " " + tAlias;

        StringBuilder whereClause = new StringBuilder();
        Map<String, Object> whereParams = new HashMap<>();

        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class))
                continue;

            Column column = field.getAnnotation(Column.class);
            field.setAccessible(true);

            try {
                if (field.get(entity) != null) {
                    String colName = StringUtils.isEmpty(column.name()) ? field.getName() : column.name();

                    whereClause
                            .append(whereParams.isEmpty() ? "" : " AND ")
                            .append(tAlias).append(".").append(colName).append("=:").append(colName);

                    whereParams.put(colName, field.get(entity));
                }

            } catch (IllegalAccessException iEx) {
                iEx.printStackTrace();

            }
        }

        jpql = jpql + (whereParams.isEmpty() && StringUtils.isBlank(whereClause) ? "" : " WHERE " + whereClause);

        jpql = jpql.replace(", FROM", " FROM");
        System.out.println("jpql: " + jpql);

        TypedQuery<T> query = (TypedQuery<T>) em.createQuery(jpql, entity.getClass());

        for (Map.Entry<String, Object> entry : whereParams.entrySet()) {
            System.out.println("param Name: " + entry.getKey() + " = " + entry.getValue());
            query = query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();

    }

    public List<User> listAllUsersNative() {
        String sql = "SELECT * FROM users";

        List<User> userList = em.createNativeQuery(sql, User.class).getResultList();
        return userList;
    }

    @Override
    public T addOrUpdate(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void addVote(int choiceId) {
    }
}
