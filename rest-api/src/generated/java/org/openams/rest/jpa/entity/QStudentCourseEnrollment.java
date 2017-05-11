package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentCourseEnrollment is a Querydsl query type for StudentCourseEnrollment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentCourseEnrollment extends EntityPathBase<StudentCourseEnrollment> {

    private static final long serialVersionUID = 759353537L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentCourseEnrollment studentCourseEnrollment = new QStudentCourseEnrollment("studentCourseEnrollment");

    public final QCourseSchedule courseSchedule;

    public final DateTimePath<java.util.Date> enrolledDtt = createDateTime("enrolledDtt", java.util.Date.class);

    public final StringPath grade = createString("grade");

    public final DateTimePath<java.util.Date> gradedDtt = createDateTime("gradedDtt", java.util.Date.class);

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> modifiedDtt = createDateTime("modifiedDtt", java.util.Date.class);

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public final QStudent student;

    public QStudentCourseEnrollment(String variable) {
        this(StudentCourseEnrollment.class, forVariable(variable), INITS);
    }

    public QStudentCourseEnrollment(Path<? extends StudentCourseEnrollment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentCourseEnrollment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentCourseEnrollment(PathMetadata metadata, PathInits inits) {
        this(StudentCourseEnrollment.class, metadata, inits);
    }

    public QStudentCourseEnrollment(Class<? extends StudentCourseEnrollment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.courseSchedule = inits.isInitialized("courseSchedule") ? new QCourseSchedule(forProperty("courseSchedule"), inits.get("courseSchedule")) : null;
        this.student = inits.isInitialized("student") ? new QStudent(forProperty("student"), inits.get("student")) : null;
    }

}

