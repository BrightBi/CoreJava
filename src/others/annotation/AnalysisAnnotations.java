package others.annotation;

import java.lang.annotation.Annotation;

public class AnalysisAnnotations {
	public static void main(String[] args) {

		boolean isAnnotation = TestAnnotation.class.isAnnotationPresent(MyAnnotation.class);
		if (isAnnotation) {
			MyAnnotation annotation = TestAnnotation.class.getAnnotation(MyAnnotation.class);
			System.out.println("id:" + annotation.id() + " code:" + annotation.code() + " msg:" + annotation.msg());
		}

		Annotation [] annotations = TestAnnotation.class.getAnnotations();
		for(int i = 0; i < annotations.length; i++) {
			System.out.println("Annotation info:"+annotations[i].annotationType().getSimpleName());
			if (annotations[i].annotationType().equals(MyAnnotation.class)) {
				MyAnnotation annotation = (MyAnnotation) annotations[i];
				System.out.println("id:" + annotation.id() + " code:" + annotation.code() + " msg:" + annotation.msg());
			}
			if (annotations[i].annotationType().equals(Persons.class)) {
				Persons annotation = (Persons) annotations[i];
				MyRepeatableAnnotation [] repeatables = annotation.value();
				for (int j = 0; j < repeatables.length; j++) {
					System.out.println("Persons:" + repeatables[j].role());
				}
			}
		}
	}
}
