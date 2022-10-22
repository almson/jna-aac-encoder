# jna-aac-encoder

[![GitHub](https://img.shields.io/github/license/sheinbergon/jna-aac-encoder?color=pink&style=for-the-badge)](https://github.com/sheinbergon/jna-aac-encoder/blob/master/LICENSE)
[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/sheinbergon/jna-aac-encoder/Multi%20Platform%20CI?style=for-the-badge)](https://github.com/sheinbergon/jna-aac-encoder/actions?query=workflow%3A"Multi+Platform+CI")
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/sheinbergon/jna-aac-encoder?color=%2340E0D0&style=for-the-badge)](https://github.com/sheinbergon/jna-aac-encoder/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/org.sheinbergon/jna-aac-encoder?color=Crimson&style=for-the-badge)](https://search.maven.org/search?q=g:org.sheinbergon%20a:jna-aac-encoder*)
[![Coveralls](https://img.shields.io/coveralls/github/sheinbergon/jna-aac-encoder?style=for-the-badge&logo=coveralls)](https://coveralls.io/github/sheinbergon/jna-aac-encoder)
[![Snyk Vulnerabilities for GitHub Repo](https://img.shields.io/snyk/vulnerabilities/github/sheinbergon/jna-aac-encoder?color=432f95&logo=snyk&style=for-the-badge)](https://app.snyk.io/org/sheinbergon/project/e56757ea-c9f2-45a1-8544-5083d087faf8)
[![Codacy grade](https://img.shields.io/codacy/grade/8f822d4bfbfa48809d439d4e8e92d287?style=for-the-badge&logo=codacy)](https://app.codacy.com/manual/sheinbergon/jna-aac-encoder)
[![Liberapay](https://img.shields.io/liberapay/patrons/sheinbergon?logo=liberapay&style=for-the-badge)](https://liberapay.com/sheinbergon/donate)                
                                                                                                                                                                                                                                                                                 
This library provides AAC encoding capabilities for the JVM. 
It utilizes the [FDK AAC](https://github.com/mstorsjo/fdk-aac) library via JNA in order to do so.

## License
**Important!** While this library uses LGPL-3, please see
the [FDK AAC license](NOTICE) for additional information
regarding re/distribution and licensing limitations.

## Sponsorship

Enjoying my work? A show of support would be much obliged :grin:

<a href="https://liberapay.com/sheinbergon/donate">
<img src="assets/liberapay-donate.png" height="66" align="center">
</a>&nbsp;&nbsp;<a href="https://www.buymeacoffee.com/sheinbergon">
<img src="assets/buymeacoffee-donate.png" height="66" align="center">
</a>

## Usage

### Dependencies
Artifacts are available on maven central:

**_Maven_**
```xml
<dependency>
    <groupId>org.sheinbergon</groupId>
    <artifactId>jna-aac-encoder</artifactId>
    <version>0.1.9</version>
</dependency>
```

**_Gradle_**
```groovy
compile 'org.sheinbergon:jna-aac-encoder:0.1.9'
```

#### Additional information
  * Single artifact containing _libfdk-aac_ shared libraries cross-compiled for:
    * Linux (64-bit) 
    * Windows (32-bit and 64-bit)
    * OSX (64-bit, compiled with Xcode 9.4.1 SDK) 

  * Provided fdk-aac version is 0.1.6
  * Both 0.1.5 and 0.1.6 versions were tested and found to comply with this bridge.
 
### Encoding using the JVM AudioSystem
```java
AudioInputStream input = AudioSystem.getAudioInputStream(...);
File output = new File(...);
AudioSystem.write(input, AACFileTypes.AAC_LC, output);
```

## Performance
Performance benchmarks comparing JNA to a BINARY application(`aac-enc`) are available using [JMH](http://openjdk.java.net/projects/code-tools/jmh/) and [JMH Visualizer](https://github.com/jzillmann/jmh-visualizer):

![alt text](assets/jmh-results-22102022.png)

To run the benchmarks locally:
  * Clone this repository onto a Linux host
  * Ensure that you have `libfdk-aac.so` __0.1.x__ library installed (either from an external repository or manually compiled)
  * Ensure that you have the `aac-enc` binary installed (either from an external repository or manually compiled)
  * Run the following command (from within the cloned repository)
```groovy
./gradlew -b benchmark.gradle jmh jmhReport
```
  * If the aac-enc binary is not installed in /usr/bin/aac-enc, you can a custom path path by adding this gradle property:
```groovy
-PaacEncBin=/CUSTOM/PATH/TO/AAC-ENC 
```
  * The JMH reports can be viewed by opening `build/reports/benchmark/index.html` in your browser.

## Limitations
Currently, **_libfdk-aac_ itself** supports only the pcm_s16le WAV input format, meaning:
  * Sample size - 16 bit(signed)
  * WAV format - (L)PCM
  * Byte order - Little Endian

While this form of input formatting seems to be the common raw-audio formatting, it's important
to note that providing input audio with different formatting will cause
the encoding process to fail. 

Additional restrictions:
  * A maximum of 6 audio input/output channels
  * Only the AAC-LC/HE-AAC/HE-AACv2 encoding profiles are supported  

## Roadmap
  * Upgrade to fdk-aac 2.0.x
  * Improved lower-level interface (with examples).
  * Support for 24 bit WAV input (via conversion).
  * M4A encoding.
  * AAC decoding ???  