var gulp = require('gulp');
var noop = require('gulp-noop');

var npmDist = require('gulp-npm-dist');
var postcss      = require('gulp-postcss');
var sourcemaps   = require('gulp-sourcemaps');
var autoprefixer = require('autoprefixer');
var mqpacker = require('css-mqpacker');
var cssnano = require('cssnano')({
    preset: 'default'
});

var sass = require("gulp-sass");
var eyeglass = require("eyeglass");

var RESOURCE_PATH = "./src/main/resources";
var NODE_MODULES_PATH = "./node_modules";

var CONFIG = {
    sass: {
        sourcePath: RESOURCE_PATH + "/static/sass/**/*.scss",
        destPath: RESOURCE_PATH + "/dist/css",
        options: {
            // node-sass options
            precision: 8,
            eyeglass: {
                // eyeglass options
                modules: [
                    {
                        name: "bootstrap",
                        main: function() {
                            return {
                                sassDir: NODE_MODULES_PATH + '/bootstrap/scss'
                            }
                        },
                        eyeglass: {
                            needs: ">=1.3.0"
                        }
                    }
                ]
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
gulp.task('build', ['copy-libs', 'sass-minified']);

gulp.task('watch', function() {
    var watcher = gulp.watch(CONFIG.sass.sourcePath, ['sass']);
    watcher.on('change', function(event) {
        console.log('File ' + event.path + ' was ' + event.type + ', running sass compilation...');
    });
});


//=  Internal tasks
gulp.task('copy-libs', function() {
    gulp.src(npmDist({copyUnminified: true, excludes: CONFIG.libs.excludes}),
                {base: NODE_MODULES_PATH})
            .pipe(gulp.dest(CONFIG.libs.destPath));
});

gulp.task("sass", function () {
    compileSass(false);
});

gulp.task("sass-minified", function () {
    compileSass(true);
});

function compileSass(shouldPostProcess) {
    gulp.src(CONFIG.sass.sourcePath)
            .pipe(sourcemaps.init())
            .pipe(sass(eyeglass(CONFIG.sass.options)).on("error", sass.logError))
            .pipe(shouldPostProcess ? postcss([
                autoprefixer(), // add vendor prefixes
                mqpacker(), // combine media queries
                cssnano // minify CSS
            ]) : noop())
            .pipe(sourcemaps.write('.'))
            .pipe(gulp.dest(CONFIG.sass.destPath));
}
