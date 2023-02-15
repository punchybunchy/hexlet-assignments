package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPreviousCourses(@PathVariable long id) {
        final String dot = "\\.";
        Course course = courseRepository.findById(id);
        String path = course.getPath();
        if (path != null) {
            List<Long> previousCoursesIds =  Arrays.stream(path.split(dot))
                    .map(Long::parseLong)
                    .toList();
            return courseRepository.findAllById(previousCoursesIds);
        }

        return new ArrayList<>();
    }
    // END

}
