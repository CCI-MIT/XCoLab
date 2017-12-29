var gulp = require('gulp');

var npmDist = require('gulp-npm-dist');
var postcss      = require('gulp-postcss');
var sourcemaps   = require('gulp-sourcemaps');
var autoprefixer = require('autoprefixer');
var mqpacker = require('css-mqpacker');
var cssnano = require('cssnano');

var sass = require("gulp-sass");
var eyeglass = require("eyeglass");

var RESOURCE_PATH = "./src/main/resources";

var CONFIG = {
    nodeModulesPath: "./node_modules",
    sass: {
        sourcePath: RESOURCE_PATH + "/static/sass/**/*.scss",
        destPath: RESOURCE_PATH + "/dist/css",
        options: {
            // node-sass options
            precision: 8,
            eyeglass: {
                // eyeglass options
            }
        }
    },
    libs: {
        destPath: RESOURCE_PATH + '/dist/vendor',
        excludes: ['/**/eyeglass-exports.js', '/build/**/*']
    }
};

//= Entry point
gulp.task('default', ['copy-libs', 'sass']);


//=  Internal tasks
gulp.task('copy-libs', function() {
    gulp.src(npmDist({excludes: CONFIG.libs.excludes}), {base: CONFIG.nodeModulesPath})
            .pipe(gulp.dest(CONFIG.libs.destPath));
});

gulp.task("sass", function () {
    gulp.src(CONFIG.sass.sourcePath)
            .pipe(sourcemaps.init())
            .pipe(sass(eyeglass(CONFIG.sass.options)).on("error", sass.logError))
            .pipe(postcss([
                autoprefixer(), // add vendor prefixes
                mqpacker(), // combine media queries
                cssnano() // minify CSS
            ]))
            .pipe(sourcemaps.write('.'))
            .pipe(gulp.dest(CONFIG.sass.destPath));
});
