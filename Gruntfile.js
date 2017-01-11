module.exports = function(grunt) {

  grunt.initConfig({
    browserify: {
      client: {
        src: ['src/client/app/**/*.js', '!src/client/app/**/*.spec.js'],
        dest: 'src/client/dist/app.js',
        options: {
          watch: false,
          keepAlive: false,
          transform: ['hbsfy']
        }
      },
      specs: {
        src: ["src/client/app/**/*.spec.js"],
        dest: "src/client/dist/all.spec.js",
        options: {
          browserifyOptions: {
            debug: false,
            transform: ['hbsfy']
          }
        }
      }
    },

    uglify: {
      options: {
        compress: true
      },
      build: {
        src: 'src/client/dist/app.js',
        dest: 'src/client/dist/app.min.js'
      }
    },

    jasmine: {
      src: [],
      options: {
        specs: 'src/client/dist/all.spec.js',
        vendor: []
      }
    },

    watch: {
      all: {
        files: ['src/**/*.*', 'test/**/*.*'],
        tasks: ['test']
      },
    }
  });

  grunt.loadNpmTasks('grunt-browserify');
  grunt.loadNpmTasks('grunt-contrib-jasmine');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-uglify');

  grunt.registerTask('default', ['browserify']);
  grunt.registerTask('dist', ['browserify:client', 'uglify']);
  grunt.registerTask('test', ['browserify:specs', 'jasmine']);
};