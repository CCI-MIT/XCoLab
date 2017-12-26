var gulp = require('gulp');

var sass = require("gulp-sass");
var eyeglass = require("eyeglass");
var sassOptions = {
    // put node-sass options you need here.
    eyeglass: {
        // put eyeglass options you need here.
    }
};

gulp.task('default', ['sass']);

gulp.task("sass", function () {
    gulp.src("./src/main/resources/static/sass/**/*.scss")
            .pipe(sass(eyeglass(sassOptions)).on("error", sass.logError))
            .pipe(gulp.dest("./src/main/resources/dist/css"));
});
