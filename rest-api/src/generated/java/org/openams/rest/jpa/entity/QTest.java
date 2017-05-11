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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTest test = new QTest("test");

    public final QCourse course;

    public final StringPath desc = createString("desc");

    public final StringPath endDtt = createString("endDtt");

    public final StringPath id = createString("id");

    public final StringPath maxScore = createString("maxScore");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath startDtt = createString("startDtt");

    public final ListPath<TestScore, QTestScore> testScores = this.<TestScore, QTestScore>createList("testScores", TestScore.class, QTestScore.class, PathInits.DIRECT2);

    public final StringPath testType = createString("testType");

    public QTest(String variable) {
        this(Test.class, forVariable(variable), INITS);
    }

    public QTest(Path<? extends Test> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTest(PathMetadata metadata, PathInits inits) {
        this(Test.class, metadata, inits);
    }

    public QTest(Class<? extends Test> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course")) : null;
    }

}

