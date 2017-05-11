package org.openams.rest.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudent is a Querydsl query type for Student
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = -1091168478L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudent student = new QStudent("student");

    public final QPerson _super;

    // inherited
    public final QContact contact;

    //inherited
    public final DateTimePath<java.util.Date> dob;

    // inherited
    public final QContact emrContact;

    //inherited
    public final EnumPath<org.openams.rest.jpa.entity.enums.EyeColor> eyeColor;

    //inherited
    public final StringPath fName;

    //inherited
    public final EnumPath<org.openams.rest.jpa.entity.enums.HairColor> hairColor;

    //inherited
    public final NumberPath<Float> height;

    //inherited
    public final StringPath id;

    //inherited
    public final DateTimePath<java.util.Date> joiningDtt;

    public final StringPath level = createString("level");

    //inherited
    public final StringPath lName;

    //inherited
    public final StringPath mName;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDtt;

    public final StringPath parentEmail = createString("parentEmail");

    //inherited
    public final StringPath pictureUri;

    //inherited
    public final StringPath prefix;

    //inherited
    public final StringPath race;

    public final StringPath rollNumber = createString("rollNumber");

    //inherited
    public final EnumPath<org.openams.rest.jpa.entity.enums.Gender> sex;

    //inherited
    public final NumberPath<Integer> ssn;

    public final ListPath<StudentCourseEnrollment, QStudentCourseEnrollment> studentCourseEnrollments = this.<StudentCourseEnrollment, QStudentCourseEnrollment>createList("studentCourseEnrollments", StudentCourseEnrollment.class, QStudentCourseEnrollment.class, PathInits.DIRECT2);

    //inherited
    public final StringPath suffix;

    // inherited
    public final QUser user;

    //inherited
    public final NumberPath<Float> weight;

    public QStudent(String variable) {
        this(Student.class, forVariable(variable), INITS);
    }

    public QStudent(Path<? extends Student> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudent(PathMetadata metadata, PathInits inits) {
        this(Student.class, metadata, inits);
    }

    public QStudent(Class<? extends Student> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QPerson(type, metadata, inits);
        this.contact = _super.contact;
        this.dob = _super.dob;
        this.emrContact = _super.emrContact;
        this.eyeColor = _super.eyeColor;
        this.fName = _super.fName;
        this.hairColor = _super.hairColor;
        this.height = _super.height;
        this.id = _super.id;
        this.joiningDtt = _super.joiningDtt;
        this.lName = _super.lName;
        this.mName = _super.mName;
        this.modifiedDtt = _super.modifiedDtt;
        this.pictureUri = _super.pictureUri;
        this.prefix = _super.prefix;
        this.race = _super.race;
        this.sex = _super.sex;
        this.ssn = _super.ssn;
        this.suffix = _super.suffix;
        this.user = _super.user;
        this.weight = _super.weight;
    }

}

