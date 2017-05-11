package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTestScore is a Querydsl query type for TestScore
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestScore extends EntityPathBase<TestScore> {

    private static final long serialVersionUID = 140381255L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestScore testScore = new QTestScore("testScore");

    public final DateTimePath<java.util.Date> endDtt = createDateTime("endDtt", java.util.Date.class);

    public final StringPath grade = createString("grade");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final StringPath notes = createString("notes");

    public final QPerson person;

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final DateTimePath<java.util.Date> startDtt = createDateTime("startDtt", java.util.Date.class);

    public final QTest test;

    public QTestScore(String variable) {
        this(TestScore.class, forVariable(variable), INITS);
    }

    public QTestScore(Path<? extends TestScore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTestScore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTestScore(PathMetadata metadata, PathInits inits) {
        this(TestScore.class, metadata, inits);
    }

    public QTestScore(Class<? extends TestScore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.person = inits.isInitialized("person") ? new QPerson(forProperty("person"), inits.get("person")) : null;
        this.test = inits.isInitialized("test") ? new QTest(forProperty("test"), inits.get("test")) : null;
    }

}

