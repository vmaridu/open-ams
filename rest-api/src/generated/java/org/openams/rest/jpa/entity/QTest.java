package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = -1755434741L;

    public static final QTest test = new QTest("test");

    public final StringPath desc = createString("desc");

    public final DateTimePath<java.util.Date> endDtt = createDateTime("endDtt", java.util.Date.class);

    public final StringPath id = createString("id");

    public final StringPath maxGrade = createString("maxGrade");

    public final NumberPath<Float> maxScore = createNumber("maxScore", Float.class);

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath refId = createString("refId");

    public final DateTimePath<java.util.Date> startDtt = createDateTime("startDtt", java.util.Date.class);

    public final ListPath<TestScore, QTestScore> testScores = this.<TestScore, QTestScore>createList("testScores", TestScore.class, QTestScore.class, PathInits.DIRECT2);

    public final StringPath testType = createString("testType");

    public QTest(String variable) {
        super(Test.class, forVariable(variable));
    }

    public QTest(Path<? extends Test> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTest(PathMetadata metadata) {
        super(Test.class, metadata);
    }

}

