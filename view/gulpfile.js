var gulp = require('gulp');

var npmDist = require('gulp-npm-dist');
var postcss      = require('gulp-postcss');
var sourcemaps   = require('gulp-sourcemaps');
var autoprefixer = require('autoprefixer');
var mqpacker = require('css-mqpacker');
var cssnano = require('cssnano');

var sass = require("gulp-sass");
var eyeglass = require("eyeglass");

var sassOptions = {
    // node-sass options
    precision: 8,
    eyeglass: {
        // eyeglass options
    }
};

var RESOURCES_PATH = "./src/main/resources";

gulp.task('default', ['sass', 'copy-libs']);

gulp.task("sass", function () {
    gulp.src(RESOURCES_PATH + "/static/sass/**/*.scss")
            .pipe(sourcemaps.init())
            .pipe(sass(eyeglass(sassOptions)).on("error", sass.logError))
            .pipe(postcss([
                    autoprefixer(), // add vendor prefixes
                    mqpacker(), // combine media queries
                    cssnano() // minify CSS
            ]))
            .pipe(sourcemaps.write('.'))
            .pipe(gulp.dest(RESOURCES_PATH + "/dist/css"));
});

gulp.task('copy-libs', function() {
    gulp.src(npmDist({excludes: ['/**/eyeglass-exports.js', '/build/**/*']}),
                {base:'./node_modules'})
            .pipe(gulp.dest(RESOURCES_PATH + '/dist/vendor'));
});
