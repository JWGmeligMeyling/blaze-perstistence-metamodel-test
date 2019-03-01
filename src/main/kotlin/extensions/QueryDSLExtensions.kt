package extensions

import com.blazebit.persistence.*
import com.blazebit.persistence.spi.ServiceProvider
import com.mysema.query.jpa.JPQLSerializer
import com.mysema.query.jpa.JPQLTemplates
import com.mysema.query.types.*
import com.mysema.query.types.Path
import javax.persistence.EntityManager
import kotlin.reflect.KClass

/**
 * Creates a new criteria builder with the given result class. The result class will be used as default from class.
 * The alias will be used as default alias for the from class. Both can be overridden by invoking
 * {@link BaseQueryBuilder#from(java.lang.Class, java.lang.String)}.
 *
 * @param entityManager The entity manager to use for the criteria builder
 * @param entityPath Source of the query
 * @param <T> The type of the result class
 * @return A new criteria builder
 */
fun <T : Any> CriteriaBuilderFactory.create(entityManager : EntityManager, entityPath : EntityPath<T>) : CriteriaBuilder<T> {
    val entityType : Class<T> = entityPath.annotatedElement as Class<T>
    val alias = entityPath.metadata.name
    return this.create(entityManager, entityType, alias)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.innerJoin(path : CollectionExpression<*, P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return innerJoin(expression, aliasName).setParameters(parameters)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.innerJoin(path : MapExpression<*, P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return innerJoin(expression, aliasName).setParameters(parameters)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.innerJoinFetch(path : CollectionExpression<*, P>, alias : Path<P>): A where T : FullQueryBuilder<*, A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return innerJoinFetch(expression, aliasName).setParameters(parameters)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.innerJoinFetch(path : MapExpression<*, P>, alias : Path<P>): A where T : FullQueryBuilder<*, A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return innerJoinFetch(expression, aliasName).setParameters(parameters)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.innerJoin(path : EntityPath<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    assert(path.root != null)
    return innerJoin(path, path)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.innerJoin(path : EntityPath<P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    assert(path.root != null)
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return innerJoin(expression, aliasName).setParameters(parameters)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.leftJoin(path : CollectionExpression<*, P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return leftJoin(expression, aliasName).setParameters(parameters)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.leftJoin(path : MapExpression<*, P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return leftJoin(expression, aliasName).setParameters(parameters)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.leftJoinFetch(path : CollectionExpression<*, P>, alias : Path<P>): A where T : FullQueryBuilder<*, A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return leftJoinFetch(expression, aliasName).setParameters(parameters)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.leftJoinFetch(path : MapExpression<*, P>, alias : Path<P>): A where T : FullQueryBuilder<*, A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return leftJoinFetch(expression, aliasName).setParameters(parameters)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.leftJoin(path : EntityPath<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    assert(path.root != null)
    return leftJoin(path, path)
}
/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.leftJoin(path : EntityPath<P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    assert(path.root != null)
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return leftJoin(expression, aliasName).setParameters(parameters)
}

/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.rightJoin(path : CollectionExpression<*, P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return rightJoin(expression, aliasName).setParameters(parameters)
}


/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.rightJoin(path : MapExpression<*, P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return rightJoin(expression, aliasName).setParameters(parameters)
}


/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.rightJoinFetch(path : CollectionExpression<*, P>, alias : Path<P>): A where T : FullQueryBuilder<*, A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return rightJoinFetch(expression, aliasName).setParameters(parameters)
}

/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.rightJoinFetch(path : MapExpression<*, P>, alias : Path<P>): A where T : FullQueryBuilder<*, A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return rightJoinFetch(expression, aliasName).setParameters(parameters)
}

/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.rightJoin(path : EntityPath<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    assert(path.root != null)
    return rightJoin(path, path)
}

/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The query builder for chaining calls
 */
fun <T, A, P> T.rightJoin(path : EntityPath<P>, alias : Path<P>): A where T : FromBuilder<A>, A : ParameterHolder<*> {
    assert(path.root != null)
    val aliasName = alias.metadata.name
    val (expression, parameters) = parseExpressionAndBindParameters(path)
    return rightJoin(expression, aliasName).setParameters(parameters)
}

/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.rightJoinOn(path : CollectionExpression<*, P>, alias : Path<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val expression = parseJPQLExpression(path)
    return rightJoinOn(expression, aliasName)
}

/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.rightJoinOn(path : MapExpression<*, P>, alias : Path<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val expression = parseJPQLExpression(path)
    return rightJoinOn(expression, aliasName)
}


/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.rightJoinOn(path : EntityPath<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    return rightJoinOn(path, path)
}

/**
 * Create a right join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.rightJoinOn(path : EntityPath<P>, alias : Path<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    val entityType : Class<P> = path.annotatedElement as Class<P>
    val aliasName = alias.metadata.name

    if (path.root != null) {
        val expression = parseJPQLExpression(path)
        return rightJoinOn(expression, aliasName)
    }
    else {
        return rightJoinOn(entityType, aliasName)
    }
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.innerJoinOn(path : CollectionExpression<*, P>, alias : Path<P>): JoinOnBuilder<A>  where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val expression = parseJPQLExpression(path)
    return innerJoinOn(expression, aliasName)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.innerJoinOn(path : MapExpression<*, P>, alias : Path<P>): JoinOnBuilder<A>  where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val expression = parseJPQLExpression(path)
    return innerJoinOn(expression, aliasName)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.innerJoinOn(path : EntityPath<P>): JoinOnBuilder<A>  where T : FromBuilder<A>, A : ParameterHolder<*> {
    return innerJoinOn(path, path)
}

/**
 * Create an inner join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.innerJoinOn(path : EntityPath<P>, alias : Path<P>): JoinOnBuilder<A>  where T : FromBuilder<A>, A : ParameterHolder<*> {
    val entityType : Class<P> = path.annotatedElement as Class<P>
    val aliasName = alias.metadata.name

    if (path.root != null) {
        val expression = parseJPQLExpression(path)
        return innerJoinOn(expression, aliasName)
    }
    else {
        return innerJoinOn(entityType, aliasName)
    }
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.leftJoinOn(path : CollectionExpression<*, P>, alias : Path<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val expression = parseJPQLExpression(path)
    return leftJoinOn(expression, aliasName)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.leftJoinOn(path : MapExpression<*, P>, alias : Path<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    val aliasName = alias.metadata.name
    val expression = parseJPQLExpression(path)
    return leftJoinOn(expression, aliasName)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.leftJoinOn(path : EntityPath<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    return leftJoinOn(path, path)
}

/**
 * Create a left join with the given target.
 *
 * @param path The path to join
 * @param alias The alias for the joined element
 * @return The restriction builder for the on-clause
 */
fun <T, A, P> T.leftJoinOn(path : EntityPath<P>, alias : Path<P>): JoinOnBuilder<A> where T : FromBuilder<A>, A : ParameterHolder<*> {
    val entityType : Class<P> = path.annotatedElement as Class<P>
    val aliasName = alias.metadata.name

    if (path.root != null) {
        val expression = parseJPQLExpression(path)
        return leftJoinOn(expression, aliasName)
    }
    else {
        return leftJoinOn(entityType, aliasName)
    }
}

fun <T> T.orderBy(expression: Expression<*>, ascending : Boolean, nullFirst : Boolean) : T where T : ParameterHolder<T>, T : OrderByBuilder<T> {
    val (exp, parameters) = parseExpressionAndBindParameters(expression)
    return orderBy(exp, ascending, nullFirst).setParameters(parameters)
}

fun <T> T.orderByAsc(expression: Expression<*>, nullFirst : Boolean) : T where T : ParameterHolder<T>, T : OrderByBuilder<T> {
    val (exp, parameters) = parseExpressionAndBindParameters(expression)
    return orderByAsc(exp, nullFirst).setParameters(parameters)
}

fun <T> T.orderByAsc(vararg expressions: Expression<*>) : T where T : ParameterHolder<T>, T : OrderByBuilder<T> {
    for (expression in expressions ) {
        val (exp, parameters) = parseExpressionAndBindParameters(expression)
        orderByAsc(exp).setParameters(parameters)
    }
    return this
}

fun <T> T.orderByDesc(expression: Expression<*>, nullFirst : Boolean) : T where T : ParameterHolder<T>, T : OrderByBuilder<T> {
    val (exp, parameters) = parseExpressionAndBindParameters(expression)
    return orderByDesc(exp, nullFirst).setParameters(parameters)
}

fun <T> T.orderByDesc(vararg expressions: Expression<*>) : T where T : ParameterHolder<T>, T : OrderByBuilder<T> {
    for (expression in expressions ) {
        val (exp, parameters) = parseExpressionAndBindParameters(expression)
        orderByDesc(exp).setParameters(parameters)
    }
    return this
}

fun <T> T.orderByAsc(vararg specifiers: OrderSpecifier<*>) : T where T : ParameterHolder<T>, T : OrderByBuilder<T> {
    for (specifier in specifiers) {
        orderBy(specifier.target, specifier.isAscending, specifier.nullHandling == OrderSpecifier.NullHandling.NullsFirst)
    }
    return this
}


fun <T> T.groupBy(vararg expressions: Expression<*>) : T where T : ParameterHolder<T>, T : GroupByBuilder<T> {
    for (expression in expressions) {
        val (exp, parameters) = parseExpressionAndBindParameters(expression)
        return groupBy(exp).setParameters(parameters)
    }
    return this
}


fun <T : CTEBuilder<T>> CTEBuilder<T>.with(cteClass : KClass<*>) : FullSelectCTECriteriaBuilder<T> {
    return with(cteClass.java)
}

fun <T : CTEBuilder<T>> CTEBuilder<T>.with(entityPath: EntityPath<*>) : FullSelectCTECriteriaBuilder<T> {
    val entityType : Class<*> = entityPath.annotatedElement as Class<*>
    return with(entityType)
}

fun <T> FullSelectCTECriteriaBuilder<T>.bind(path : Path<*>) : SelectBuilder<FullSelectCTECriteriaBuilder<T>> {
    val expression = parseJPQLExpression(path)
    val alias = generateSequence(path) { elem -> elem.metadata.parent }.last().metadata.name
    val unqualifiedExpression = expression.substring(alias.length + 1)
    return bind(unqualifiedExpression)
}

fun <T : ParameterHolder<*>> JoinOnBuilder<T>.on(predicate: Predicate) : T {
    val ser = JPQLSerializer(JPQLTemplates.DEFAULT)
    predicate.accept(ser, null)
    var jpqlQueryFragment = ser.toString()


    val parameterNames = ser.constantToLabel.values.map { it to "qsl_param_${Counter.getCount()}" }.toMap()
    for ((label, parameterName) in parameterNames) {
        jpqlQueryFragment = jpqlQueryFragment.replace("?$label", ":$parameterName")
    }

    val result = this.setOnExpression(jpqlQueryFragment)

    for ((constant, label) in ser.constantToLabel) {
        result.setParameter(parameterNames[label], constant)
    }

    return result
}

/**
 * Adds a select clause with the given expression to the query.
 *
 * @param expression The expression for the select clause
 * @return The query builder for chaining calls
 */
fun <T, A> T.select(expression: Expression<*>): A where T : SelectBuilder<A>, A : ParameterHolder<*> {
    val (expr, parameters) = parseExpressionAndBindParameters(expression)
    return select(expr).setParameters(parameters)
}

/**
 * Adds a select clause with the given expression and alias to the query.
 *
 * @param expression The expression for the select clause
 * @param alias The alias for the expression
 * @return The query builder for chaining calls
 */
fun <T, A> T.select(expression: Expression<*>, alias: String): A where T : SelectBuilder<A>, A : ParameterHolder<*> {
    val (expr, parameters) = parseExpressionAndBindParameters(expression)
    return select(expr, alias).setParameters(parameters)
}

/**
 * Sets the given expression as expression for the where clause.
 *
 * @param predicate The where predicate
 * @return The builder
 */
fun <T, A> T.where(predicate : Predicate) : A where T : WhereBuilder<A>, A : ParameterHolder<*> {
    val (jpqlQueryFragment, parameters) = parseExpressionAndBindParameters(predicate)
    return setWhereExpression(jpqlQueryFragment).setParameters(parameters)
}

/**
 * Sets the given expression as expression for the having clause.
 *
 * @param predicate The having predicate
 * @return The builder
 */
fun <T, A> T.having(predicate : Predicate) : A where T : HavingBuilder<A>, A : ParameterHolder<*> {
    val (jpqlQueryFragment, parameters) = parseExpressionAndBindParameters(predicate)
    return setHavingExpression(jpqlQueryFragment).setParameters(parameters)
}

/**
 * Set the sources of this query
 *
 * @param entityPath The entity which should be queried
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>> T.from(entityPath : EntityPath<*>) : T {
    val entityType : Class<*> = entityPath.annotatedElement as Class<*>
    val alias = entityPath.metadata.name
    return this.from(entityType, alias)
}

/**
 * Set the sources of this query, but explicitly queries the data before any side effects happen because of CTEs.
 *
 * @param entityPath The entity which should be queried
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>> T.fromOld(entityPath : EntityPath<*>) : T {
    val entityType : Class<*> = entityPath.annotatedElement as Class<*>
    val alias = entityPath.metadata.name
    return this.fromOld(entityType, alias)
}

/**
 * Set the sources of this query, but explicitly queries the data after any side effects happen because of CTEs.
 *
 * @param entityPath The entity which should be queried
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>> T.fromNew(entityPath : EntityPath<*>) : T {
    val entityType : Class<*> = entityPath.annotatedElement as Class<*>
    val alias = entityPath.metadata.name
    return this.fromNew(entityType, alias)
}

/**
 * Add a VALUES clause for values of the given value class to the from clause.
 * This introduces a parameter named like the given alias.
 *
 * @param entityPath The entity which should be queried
 * @param valueCount The number of values to use for the values clause
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>> T.fromValues(entityPath : EntityPath<*>, valueCount : Int) : T {
    val entityType : Class<*> = entityPath.annotatedElement as Class<*>
    val alias = entityPath.metadata.name
    return this.fromValues(entityType, alias, valueCount)
}

/**
 * Add a VALUES clause for values of the type as determined by the given entity attribute to the from clause.
 * This introduces a parameter named like the given alias.
 *
 * @param entityPath The entity which should be queried
 * @param attributeName The attribute name within the entity class which to use for determining the values type
 * @param valueCount The number of values to use for the values clause
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>> T.fromValues(entityPath : EntityPath<*>, attributeName : String, valueCount : Int) : T {
    val entityType : Class<*> = entityPath.annotatedElement as Class<*>
    val alias = entityPath.metadata.name
    return this.fromValues(entityType, alias, attributeName, valueCount)
}

/**
 * Add a VALUES clause for values of the given value class to the from clause.
 * This introduces a parameter named like the given alias.
 *
 * @param entityPath The entity which should be queried
 * @param valueCount The number of values to use for the values clause
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>> T.fromIdentifiableValues(entityPath : EntityPath<*>, valueCount : Int) : T {
    val entityType : Class<*> = entityPath.annotatedElement as Class<*>
    val alias = entityPath.metadata.name
    return this.fromIdentifiableValues(entityType, alias, valueCount)
}


/**
 * Add a VALUES clause for values of the type as determined by the given entity attribute to the from clause.
 *
 * @param entityPath The entity which should be queried
 * @param values The values to use for the values clause
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>, A> T.fromValues(entityPath : EntityPath<A>, values : Collection<A>) : T {
    val entityType : Class<A> = entityPath.annotatedElement as Class<A>
    val alias = entityPath.metadata.name
    return this.fromValues(entityType, alias, values)
}

/**
 * Add a VALUES clause for values of the type as determined by the given entity attribute to the from clause.
 *
 * @param entityPath The entity which should be queried
 * @param attributeName The attribute name within the entity class which to use for determining the values type
 * @param values The values to use for the values clause
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>, A> T.fromValues(entityPath : EntityPath<A>, attributeName: String, values : Collection<A>) : T {
    val entityType : Class<A> = entityPath.annotatedElement as Class<A>
    val alias = entityPath.metadata.name
    return this.fromValues(entityType, alias, attributeName, values)
}


/**
 * Add a VALUES clause for values of the type as determined by the given entity attribute to the from clause.
 *
 * @param entityPath The entity which should be queried
 * @param values The values to use for the values clause
 * @return The query builder for chaining calls
 */
fun <T : FromBaseBuilder<T>, A> T.fromIdentifiableValues(entityPath : EntityPath<A>, values : Collection<A>) : T {
    val entityType : Class<A> = entityPath.annotatedElement as Class<A>
    val alias = entityPath.metadata.name
    return this.fromIdentifiableValues(entityType, alias, values)
}

/**
 * Adds an implicit join fetch to the query.
 *
 * @param paths The paths to join fetch
 * @return The query builder for chaining calls
 */
fun <A : FetchBuilder<A>> A.fetch(vararg paths: Path<*>) : A {
    for (path in paths) {
        var jpqlQueryFragment = parseJPQLExpression(path)
        this.fetch(jpqlQueryFragment)
    }
    return this
}

private fun  <A : ParameterHolder<*>> A.setParameters(parameters : Map<String, Any>) : A {
    for ((parameterName, constant) in parameters) {
        setParameter(parameterName, constant)
    }
    return this
}

private fun parseJPQLExpression(expression: Expression<*>): String {
    val ser = JPQLSerializer(JPQLTemplates.DEFAULT)
    expression.accept(ser, null)
    assert( ser.constantToLabel.isEmpty() )
    return ser.toString()
}

private fun Any.parseExpressionAndBindParameters(expression : Expression<*>) : Pair<String, HashMap<String, Any>> {
    val em = (this as ServiceProvider).getService(EntityManager::class.java)
    val ser = JPQLSerializer(JPQLTemplates.DEFAULT, em)
    expression.accept(ser, null)
    var jpqlQueryFragment = ser.toString()

    val parameters = HashMap<String, Any>()
    for ((constant, label) in ser.constantToLabel) {
        val parameterName = "qsl_param_${Counter.getCount()}"
        parameters[parameterName] = constant
        jpqlQueryFragment = jpqlQueryFragment.replace("?$label", ":$parameterName")
    }

    return Pair(jpqlQueryFragment, parameters)
}
