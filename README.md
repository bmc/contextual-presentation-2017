# PHASE Contextual Presentation

This repo contains the source for my 2017 [PHASE](https://www.scala-phase.org)
talk on Jon Pretty's [Contextual](http://contextual.io) library.

## Preparing to build the slides

1. Install [NodeJS][] and `npm`.
2. Install the [LESS][] preprocessor: `npm install -g less`
3. Install Bower: `npm install -g bower`
4. Run `bower install` locally.
5. Make sure you have a version of Ruby 2 installed. (This stuff has been
   tested with 2.2.3.)
6. Install Bundler: `gem install bundler`
7. Use Bundler to install the required Ruby gems: `bundle install`

## Building the Slides

Once you've successfully completed preparation, building the slide deck
is as simple as:

    $ rake

Rake will build `dist/index.html`, a [Reveal.js][] slide show. Just
open the file in your browser, and away you go.

## Installing the slide show

If you want to install the slide show somewhere (e.g., a web server), copy
the _entire_ `dist` directory (presumably renaming it).

## Making PDFs

To create PDF versions of the slides, open the HTML slides in Chrome or
Chromium. Then, tack `?print-pdf` on the end of the URL, and print the result.
See the [Reveal.js][] documentation for details.

[Ruby]: http://www.ruby-lang.org/
[Rake]: http://rake.rubyforge.org/
[Bundler]: http://gembundler.com/
[LESS]: http://lesscss.org/
[Reveal.js]: https://github.com/hakimel/reveal.js
[NodeJS]: http://nodejs.org
[PHASE]: http://scala-phase.org
[Northeast Scala Symposium]: http://www.nescala.org
