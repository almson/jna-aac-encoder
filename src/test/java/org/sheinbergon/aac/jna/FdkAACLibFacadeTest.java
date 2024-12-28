package org.sheinbergon.aac.jna;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sheinbergon.aac.jna.structure.AACEncBufDesc;
import org.sheinbergon.aac.jna.structure.AACEncInArgs;
import org.sheinbergon.aac.jna.structure.AACEncOutArgs;
import org.sheinbergon.aac.jna.util.AACDecParam;
import org.sheinbergon.aac.jna.util.AACDecodeFrameFlag;
import org.sheinbergon.aac.jna.util.FdkAACLibException;
import org.sheinbergon.aac.jna.util.TransportType;

@SuppressWarnings("MissingJavadocMethod")
@DisplayName("Fdk-AAC Library Facade")
public final class FdkAACLibFacadeTest {

  private static final int SAMPLE_DATA_LENGTH = 128;
  private static final byte[] SAMPLE_DATA = new byte[SAMPLE_DATA_LENGTH];

  @Test
  @DisplayName("Invalid call result verification")
  public void invalidLibCallResultVerification() {
    // Passing a null encoder instance triggers an invalid return code;
    Assertions.assertThrows(NullPointerException.class, () ->
        FdkAACLibFacade.encode(null,
            new AACEncBufDesc(),
            new AACEncBufDesc(),
            new AACEncInArgs(),
            new AACEncOutArgs(),
            SAMPLE_DATA_LENGTH));
  }
  
//  @Test
//  public void testIsobmff() throws IOException {
//    var path = Path.of("/home/alex/work/syncwords/code/live-core/temp/out.cmfa");
//    var pathOut = Path.of("/home/alex/work/syncwords/code/live-core/temp/out.raw");
//    var data = Files.readAllBytes(path);
//    var buffer = ByteBuffer.wrap(data);
//    var decoder = FdkAACLibFacade.openDecoder(TransportType.TT_MP4_RAW, 1);
//    FdkAACLibFacade.setDecoderParam(decoder, AACDecParam.AAC_PCM_MAX_OUTPUT_CHANNELS, 1);
//    FdkAACLibFacade.decoderFillISOBMFF(decoder, buffer);
//    var info = FdkAACLibFacade.getDecoderInfo(decoder);
//    var samples = ByteBuffer.allocate(1024*1024);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples);
//    FdkAACLibFacade.decoderDecodeFrame(decoder, samples, AACDecodeFrameFlag.AACDEC_FLUSH);
//    samples.flip();
//    var outData = new byte[samples.remaining()];
//    samples.get(outData);
//    Files.write(pathOut, outData);
//  }
}
