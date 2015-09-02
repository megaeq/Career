package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class OCR
{
	
	public static void main(String[] args)
	{
		String httpUrl = "http://apis.baidu.com/apistore/idlocr/ocr";
		String httpArg = "fromdevice=pc&clientip=10.10.10.0&detecttype=LocateRecognize&languagetype=CHN_ENG&imagetype=1";
		//httpArg +="&image=/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDABMNDxEPDBMREBEWFRMXHTAfHRsbHTsqLSMwRj5KSUU+RENNV29eTVJpU0NEYYRiaXN3fX59S12Jkoh5kW96fXj/2wBDARUWFh0ZHTkfHzl4UERQeHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHj/wAARCAAfACEDAREAAhEBAxEB/8QAGAABAQEBAQAAAAAAAAAAAAAAAAQDBQb/xAAjEAACAgICAgEFAAAAAAAAAAABAgADBBESIRMxBSIyQXGB/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/APawEBAQEBAgy8i8ZTVV3UY6V1eU2XoWDDZB19S646Gz39w9fkKsW1r8Wm2yo1PYis1be0JG9H9QNYCAgc35Cl3yuVuJZl0cB41rZQa32dt2y6OuOiOxo61vsLcVblxaVyXD3hFFjL6La7I/sDWAgICAgICB/9k=";
	//	httpArg +="&image="+"/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCAA2AcUDAREAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD+/igAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgD8tP+Ch/wC2z4v/AGe/hn8e4/hPZfE2y8VfD79n/wCP+q3viZP2Nv2vfF3hzwt8QE+BereN/gp8SfBnx10T4B+PP2bNc0Xwf4tsNP0T4meFfH+p2vga1sPEV/4s8X/FfwpdfCjxX8JPGAB9M+Ef22fgN4y+IHgL4ZWtl+0B4V8VfEzWtY8N+A0+K37G37ZXwY8OeI/EeieBvF/xF1LQLLxx8YfgH4F8GR61H4M8C+LPEdtpV34ghv8AULDRNTGm29zcw+SwB4zpf7enwC0b9oX9pQfFH9rX9n/4a/Bj4d614F/Zx8F6P8TPiz8H/A82qftNfDLRNW+Jn7Uup+FNZ1bWrKfxPoug+Fv2gP2b/hn4k0u68QTax4I+K/gb4l+GdX8B+FpFt/EviYA2f+Ce37aHwl/ak/Zz/Zvg/wCGlfgz8W/2kdS/Zm+D/wAQvjl4O8G/ET4Xaj8RtI8Y3ngTwNH8TNY8WfDXwTqaXXgn+z/HXiAaXr2n/wDCO6RpXh3XtQs/Dv2Gwmks9OoA2vGf7TF9oHjf4yfFq0u9a1f9mv8AZv8Ahl4i8GanpngzRvDev61+0p+1v4g+IWn6Inwb+DcmtTeHtR1n4m/BbUfA+nfA/wAOeHPCGv6r4P8AjH+0V+0VqP7P11qWkfHf4D+MPCEIBi/8FHv2ovHv7J/7OfjD4l/CyfyfHnhjwZ8TfiNpsGufskftM/tI/DnX9L+GvgTWddvPB/jXxL8BvEHg3R/2ef8AhI9Yn8NpafFv4teL7bwZo+g23jDUX8NaxZ6ZrusaOAfJviP9u3xf8P8A4mfBf4XfDrxf+1n8V5x+z/8AtO+Mtfu/2i/+Cbn7XuheI/ir8QPCnx1/Y8ufANprenfBX9hb4ba/4b0Wy+H3jT4tfC0fFT4VfCfWvA3wrv8AxR8PPHHxn+HPxM8aDwJ4J8TgH1l+3X+2X8Of2fP2Lvi58UbX43+DPgl8VfFv7M3xq8efsxaZ8XbrQfhz8RvE3xG0L4TX3iDwjYeHfhB8aLPRPEWt+M9E8Q6z4Nt9X+Guu+DbjXtO17U9L8KeMPCcWpXj6NIAY37M/wC274j/AGjPipaeAtE1/wD4JzeMdNtNF1nxJ4vX9mf/AIKTX/7RnxU8PeHLCGKxtdftPhXafse/DuDUtFfxTqnhnw/rOq6p420Gw0aHW4r2O51HVl0zw3fgHmXin/gpR8Ofh/8AtYeJfC+s/E3wZrfwg1L4zeHP2MtD8MWHiHQdR8e6P+0X8J/2ePjz+0p+0X4t+Hfg3wR4f8XeOvjf/ad149/ZF/ZMsfhfol2/jyy/aZv9Z+HWkeAdP8b3nhPSvHgBjfsi/wDBSe+8Zfso+I/ip8bvh58TZH/Z6/4Jzfsv/thfFH4lX/h/w34Y8R/He+8b/DX496l8WNW+Gfwqlj8IQaJotp4p/Z28X2PgzXNffwd4b+I1/qEniP4d6LB8BH+GHxd8VAB4N/ak/bT8eaRd/s123wM+Jvw6/bf0fWvhX+0T4mi+MB+A1t+z14e/Zx8e/tQfEfxZf/CzUfjx8DNZ/aD0SDRbL4afCvxZ+xx4J8Y3fwztf2l/iLrdx4c/aQ034D6XoenfG/x74HAPkz9tr/gsH+0X+y9ceK/D3iXwt+zN8CPF3w2/4af0LxDHrepePP2nvB3xB+I3wz+An7CHxm+B/gvwLqv/AAl37EPjrTf+FsXX7X0Pw+8T3Wg/DD4o694G1XSYfiLq/hCL4G6L8WPin4fAOz8Hf8FivtH7K3xS+MGq/Gv9jPxJ4ysv2zPhD8CP2etW8V63/wAM1fDn45/BzxHe/sQ6l8TPiFdeHtR/aB/aR8YeAf8AhAfB/wC0f4p8Q+NdZfUvEHir4H6Dp+gax+0H8A/DXjzw98R/gkoB+jPwx/aisPiv+018PPCfhLxTrVp4V+If7Jl78Zbz4b+Mfh14kaxF9Zax8Adc8KeN/gN+0N4L8G61+z18bNFPhr9oGXwv+0LL8Pf2h/iv4b0vxJYfAP8A4VDDpts/xv8AEWsAHpnwF+IXjHxr8Vv23/DXifWP7S0b4R/tM+Evh78PLL+z9Ks/+Ef8Hal+xP8Asc/FW+0f7TYWNrdav5/jr4neNdf/ALQ1yfUNVj/tj+yIb5NB0/RNMtgD4a1n9tD4wX2mtafBz4iaLdJr/wC3N8Qvgzaa78av2Mf2qNU+Lvw0+Fekf8E+viB+27qPgjxd+y5baf8Asn/Evxp8TdN1vw3/AMIh8HYvAdhqk3ib9n3WfhJdapN8SPj5c+N9dvQDzL9mn/goN8Wvib4O+D/jL9pz49fBn4JfCr4//sZ/FX4x6r8Tov2aPij+yTqn7PnxO0LVv2IPDuiS+GPiL+2D8X/ip8Lfjj4Mlvv2vI7DwL8UbT4dXHwi8c+M/D2gR6JdeKNOvNf8HgA9m/aJ/bK/ac+Hn7DPxX+I/hr9n3xn408eaD+xnrXxRsv2uf2dvFX7Ivif9kIfEaT9nOTx3c/Fz4Uad8Vf2oLf4ueLvgz4R8WTTaroqa/8Htc17xL4Y0mOXSPDHjKzvtMl1MA+5v2dvEPxd8T+HPHWqfFzwV8TfAU8vxN8TT+BPDHxg079n63+IGl/D/U7DQdcsNK1HVv2c/jT8X/h94r0Xw7r+q+JPC/grX55PB/jkeBdH8N6D8RfDHiTxppOsfHrxmAe/wBAHxn+1z8avjv+z58Of2iPix4L8G+DPE3gP4a/sZ/tEfGrQtf1wXMH/CJfHf4L6C/iPwn4N8aaVa+NLLWPiF4M+NOj6tc3toPCmn+Ern4b/wDCqPGFt4h8aa/e/FPwHB4aAPAPgf8AtD614q+OPwq0r4v/ALUP7QC+K/GmteKPB3gb4SaX/wAEwf2iv2PP2ZfiXfaX8MfHvj17DxL4m/ap+CvxX+IJ+JulaB4Y8ZeNrd/Cn7U/gbw3rej+DPD2lWXwvmudP8dXPiUA8yvv2yP2l/tk19ffHP4M+Dv+Ew+M37cHgz4VfCrwZ/wS6/bq/a4+I3/Cuf2R/wBq/wATfs7az4s8W6z+zv8AtXd/+Lf6hr2vah4A8HaCNe8Y2mi6LaTeUTQB2fwn/bc8e2Pj3wBq3xj+OHwZ+InwH+InwZ/ar8W2Wt+B/wBjP9pn9m/4teHvi1+zh+01+zN+zbc/Cyb4XfFb4/fGb4ieLPGfiz4ifGbxB8ONC+E+n/DXSfij4h+KOk+FvCXgbTPEOs+IdM0O5APk3xB/wUg/bXj+EXwbk+Hnwp0X4oeOfi/ov7J37YME3gXSP2sfiH8QPA37Lv7S/wC0FbfEGL4TfEHwl8D/APgmR8ZfhV4d0XwP8KtF+J/7Nq/E2b4mv8ZPHPgb4b67+0p4O+GE/wAb9T0X4OOAfWXxL/ak/ag+Kv8AwTs8bfH74KeEv+EK+KrfGbwF4H8M6f8AAu2+N/xZ8Y6f4O8L/tZ/Dv4P/tCzar4L+M//AATy0z4peDfGfg2x0v4z+H/F2h3X7H3xQ1XwRpXht/iBonhbxdqSx+F4QD4a8IftC/8ABSW4+JfwT0+28W/tZ69pur/tAfs7aD410vxJ8DPipc+HJvhXr3x2+HuifF6fX5PFX/Btr+xxomk6LYfDO+8V6hqviW7/AGjfhxN4WsLe48Vabq2q6tptl4R1EA/pKoA+Y/2t/wBp/wAP/skfBPxl8XdY8D+O/iRfeHvDPi7X9H8DfD/w5q+ralrTeE/DGreKNVl1rXbXTrvQ/h/4X03S9Jub3XfG3jC6sdC022VbS0bU/Et3oPhy+8jN80nluHlKhhfreJ9jOtCnVrfU8DTo08RhsNUxGOzKdKtSwlCFXF4aEacKeIzHF1asMLlOXY7GuOGl62TZVLN8dg8J7V0YYrMstyqDpUlicXXx2aYieHwODwOBVWlPFYrEzp1OV1KuHy7CUoVcdnWZ5dk9DHZrS9kg8f8Ahy0+GUPxV8YarovgjwpbeB4/H/ijW/EmuWGm+HPCHhyHw/8A8JDreq6/4k1N9N07TtF8PadHc3mq65qLWVha2FtcahePbW0crL9Rm+X/ANlZvmuVe29v/ZmY43L/AG/s/Ze3+qYrEYb23suep7L2nsOf2ftJ8nNy88uXmfyuR5l/bOSZPnHsfq39qZXl2ZfV/ae29h9ewlPE+x9t7Ol7X2XPye09nT57c3s4X5V+EH7bP/BSv4kfs/fskaJqHwe/bJ/ZM+I3xO1b4ZftU+IND+JfhfXv2S/iRrXjbwR4P8Y+NfB37OHxN0mw8Y/ts/sradL8Tdc07wnf6f8AFI/BL9nL9on4e6l+0V4e+JnhXwD8CLLw74e8MfCrxD5x6h9Zfs1f8FFtR+IPwI8UfEHX18GftG+PPD/xm8GfDrUvBn7NXjf9gnwbqnhfQvifb6VoXwn1bxRa3v8AwVk/aL+G8P8AwsD4kJqnw78GRz/HzSviR428Z3uleF/C/wADpbPTrvxnqABja9+2f+25d/H39oLwP8MP2FP2gNV1Lwj+z/8Aso+PPAvwh8d+JP2C7Ox07xH4l+MH7XFn491Pxb420D9tqOfRNF+O/hb4baF8OfB/ifR9c+JV/wDCTxJ4L1r4m658AtY0oWPg3x6AbP7U3/BQHxToP7Nfw/8AFnwd8FeM/h548+Nv/Cs7rw5r/jf4bfGb4p+DvDPw5+KHxAuvA3hfXvBXxx/Yn/Z0/wCChn7OfiP4zfFKRvD1r+z34S1DW/GGgnVfiB8NfHPjrwR4tszZ/AnxSAeM/Dz/AIKw/HrV/jF+zz4b+MP7GPjP4UeDfih4M+IOufEPwN4b+DH/AAUy+KX7Rfw5/sTwr4Y1ux8aP4Zv/wDgmv8ACnwf4n8GfDvxhqOh/BH4u2nw18QfETZ48+MPwl8X6F4ws/Aega5PrwBxnxt/4Kf/ALWPw3/bRuf2YNC/Zp8Z67rMH/C+/Afg/VdPj/Z4Pwc8V6p4r+LX7Htv+zr8TvH2v6v+07p9r4A8Z/DHwL8RvFPh2T9nfxn8cvhR4z/aI8VfF79jiW7034N69+1D8K/Cnw+APsz4b/tva637Snx0+GXir4c/Gbx38NNa8Z/tEeMvgJ8V/A/hD4b674O0H4OfsgeAPgD8Gv2nvCc3gTw94yt/2kvF3jPwj+2FcfEPwRoWg6T8FPGfir4gar4y8LXnwnu/Efwmgj1jSADzOw/4KK6lZftXeA/A/jPRP2gP+FY6p8Mv2u/ElvpvhP8A4J2f8FBdO8R+LodC+Jf7GA+B+v6x4D8Vfsr3nxB0zWvhvoHxA+L3w58e6r8Ptc8T/CjWr9vBnxM8a3PgXxF8S/hf8DfDgB7/AON/26bbUfAUXjL9nD4ef8J3/wAI98Ztc+E/xvu/2k4vj3+xr4O/Zw0vwz+zN4q/aX8Q/En466x8Sv2ZPFHjDwD4Mt/B9l4DsNM8R618OrXwZqeq/EvwVGfGVr5pSQA8At/2pv8AgpL4J8Oat8fviP8Assfs/wCkfArx1rXh+4tNL+IH7V/xU8JeI/2aPClrYeKdE1H4tfFSS2/4J16d4p8Ofs/+OYPD3gPx1dab8QfBFz8bP2c7/wAdeOPGf7Ut94H+DuleOfCPwZAPrLUP2nviN4L/AGJfjp+1P8WPgP8A8IP48+BXgz9q7xXr/wAFofHGvXul+IP+GcfEXxa0nSrrwx8R/Enwo8EaxdeDPjPo/wAONP8AG/gXxpe/C+08zwZ4t0LXbbQNTs/IlugD4a8ef8FB/wBqa1+OMPwEu/B/wy+BPirwnrXx/wBB+Id94E/Z+/bG/wCCmNjrF98Ovhj/AME6/ij4In8JeH/gDof7LXxB8J6Lq+gftqahZeMPEvi/wPe+G9H8SeEdF0K11Y3PibRpJwD7l/Yq+MHxu+Mvhb4y6h8atO/5E74zN4M+GXjD/hlj9oT9kf8A4WR8Of8AhTPwX8Xz+LP+FJftH+LvGPxE0r+yviJ4w+IXw9/t6TVv7B1//hD/ALfpFpDi7ZwDwD9sH9t/4y/D/wCAX7cV98IP2WP2gLfxX8APhl8drW1+Nul+Mv2A/Engj4deN/C/wg1Lx54Q+IfiXwZqH7YOsfEG10W18P6t4L+LkHgvxX8IJPiFqPw91nw7eXvwnu7nWbPw5OAemeDP2u/jXH8YvFvgz4z/ALHPxm+GHgO/8Ga78UPhv4hutd/Za8W/Eaz8HfDvwraj4u6br3wQ+B37XPx7+LnxX/sPxZeeCLXwl4v+B/gLXtV1vXvjF4I+EniP4R+Gr3w1pvxZ8cgHmfxl/wCCivgvQviR+yPpfg3RP2s9H8P+Mf2gPEvhv4radqn/AATs/bosL7xV8P7X9kv9qXxdp2geGrTxV+yvFreu61bfEvwp8OvFE+lfDWO58cw+G9B8Q67qNuPhdpXxKvYwD0z4vftff238CPib42+Bfgz9pnTdZ+Hf9ia5rvijxh+zf/woHS/Auhadb+I/Gl5408Y2/wDwUP1r9jfwL4++DNva+Arrwx8b7T4dfEzT/iRoPgzxKLjw94w+HGv3+gfF/RgD5m8R/t3fte/Er4v/AAP0X4Afs6+DPBng2/8A2mfCfwg8SeG/2ifjP4n+Ffxa8W6p4x/4JWeJv23J/hn8V/h9pf7Jnxm/4UT/AMK//wCEx8MtrWt+F/G/i3xnN8Ufhq3w41Dw9ZeA/FniTxNZgGN8eP8AgpZ8QIfgd+yl8QPhr4n/AGGfgz4g/aA+GX7FX7R+o+Ef2jv2/vA3wc+Kmh/D/wCJXxP8CeJPiv4S03wR4+/Z313RPFnwym+Glt4t8G6p8a7DX9J8c28H/Cea38LPhLq/xR8J+DvCGtAHz/8ADn/gqR8f4PHvhebxz8ff+CTWpeEY/wDhs7+39P1f/grL8DdF0u6/tH9pnwZe/sv/AG3WPB/7KviHxNp//CE/BWLxH4Z+HX9iaLqH/CzPBl3qPj/9pr/hWHxz07wz8Mb4A+s/iv8Atq+L9F/bf8M/Bm/8d/tAeGvgza6L8TviFJpXwc/4Jlfte6t8VNT8R/Arxt+z54L1T4b678RPFHwb+Onhb49/s/8AxVn+J3jfV/EPxQ/Zx+Ffw/v/AARDpfwj0rSP2gbaTxl4f1bxMAdn+xL+2z4v+O3i+98NeJrL4m67oeufE39srw3oOveJP2Nv2vfhdN4Rm+F37X3x80f4Y6Br/wATtX+AekfAi60W6+BGj+GPCWq6V4t8T/D74r/DP4r/AA+uPAfj23+JXxe+JWvaF4GAP1LoAKACgAoA+M/jd/xkN8RfAv7O/g7/AImPhz4XfGb4LfGX9pvxfa/urPwF/wAKh1/QP2g/gl8JtB19vtljcfGbx98UvDHwb8X+LfAh0bVf+Ee/ZRl8beI/G2rfDnxX8Tv2SvEPigA8Z17Sf7Z/a5+DHw28G/GH/hdGq/Bz9szx3+0z8Q/AUep/29rv7JPwc+IX/BP349fDix8H/EjxTf8AiTxBqlt/wsP46fGgePfgp4M8YXukeKp/hv491fwj8CPh/J+zf8A9b1Hw8Aem/sceG/Dng34of8FCvB/g/QNF8K+FPCv7Wfwu8N+F/C/hvSrDRPDnhvw5on/BND/gnfpeiaBoGiaZb2unaNoujada22n6VpWn20Fhp9hBb2Vnbx20UaAA4z9kfS7DWP8Agkp+x5aav4l+JvhXQ4f2Gf2XNU8S6p8GbfxJcfFS68KaJ8D/AId614q8NeCI/BXh7xJ8QV1rxpoGnal4Qin+E1hB8bLWHWbi7+B/iHw38XofBXi6xAPGfDf7Rnwa8ZeI9Ag8bfA39rP4a/AT4Ea1pWjfs7fs4aD/AME0v2/YfDmoX3wy1C3sfA3xt+I2leE/2VrnwtDovhSfQtN1f9lL4GaVJN4b+F1hZeF/j98TLWT9pUfCP4d/A4A8Z/bc/a+sPj9/wTF/ao8WSfF3Rf2P00j4ZftO/s+/HD4SfGz4X+JPBvxd8afH0fs83fj34e/BT4I+J/2hE+EU9novxn8Lalp3inQ5tb/Z31r4wfFb9n3x9p3/AAifhb4AfHzT9XufDoB6Z+1B8GNC8QeKfDnwz+FMnxm+Jfw5+CfwZ+LvwN/bDt/7W+JH7RHjrTPgB+0/8Zv2R/i38XPht/wn/wAR/iX4j+MHxR+M3jz4P/CPX9B/4VB4B1jWvjn8Nf2b/iWPjV8N9Q0n4q6f+wn+zp8ZQDG8TftL+I/jZ4c+FH7fvwj8S6L8G5/2f/2GfD3xD+Ptt4y+B9/+0z8M/B/w/wD22bH9n74+eO/DlprnhL9pz9l/X7n4m/sofD79nvw18Y/ip4Uh0698c638CviT8OPEfw6+FniTxp47+Hfg68APTPBHjL4jab+0Xpfx9/aW+LngzxT/AMKM8Z+Ov+Ce2teFPhZ+yzr3wk/4V98Rv2rPHn7IPjL4aePPiBruvftr/tH/APCY+DPij/wjX7O2m/DyH4Y6Rfa94a/4X94bvvjjpngr/hEfjhZ+DQDxm6/4Vz8DP2hNe8UXv/CGfCD4D/AT/grL4S/4SbxDdf2D4C+DnwW+HL/8G9Pgn4ReBv7e1aX+yPB/w28GP4w8ZeAfhf4S/tC40rQW8VeJPBPgPRd2s6xoGlTgHybY+Kvgt4R/Y8/a7+DPhb9rX9n/APab+Icn/BBr/hVNzpX7MeveEvG/gj4UeHP2DvgF408GeL73x78RNP8Ai3r+t+J9a+MfxL/bB1nV/hbcyfCr4dWA+Hvg7UtK1bTH8T6NfarqgB9mfs//AAp+A3xx+Pv7fXwn8EXv7Wej+CvGP7Jn7DFhJ4o/aDuf2yrD4++BfiBa/GD/AIKIan4Z+Jnwf1v9vDTNX+JfhbWvhhrdj4U+IHwe8Z+GtPufA3g74waCfF/hFF+IGk+KpQAfJn7T954a/aNs/wBs7wd8LPGngzxBo37Yfxm8e6J8Bvi1a/tl/th/B74OeL9L8Zfso/8ABJ79hu38daDYfsm/CT4ufCn9r7wZ4f8A2mvir4c+Efi3wx8WtYs/BmgeKrHxv4MeaXQYv2gLrQQD2a28VeINS/YuuNC+A3iTxn+3ZrOifGb4Yftf6hrvhTw5+3Daf2b4O/Z8+E37MX/BRD4W/DK1vP2u/jx+1z461Hxn+0bdQfs8/CXwV4G8JfEK01XTf+GgvEHxR8JfAPxFr3wP/aY8P3gB9zfHvxJ4jsfj7+yd+178Otf+GV/+zrof7P8A8efDfj74y3Oq3/juxvPDnxy+MP7BXiv4W6B8Dfh/8OLm88U/Hv4m/tFQfDHVPh38E9K8F3Mlg/iTxJofijS7f4heLovh3+zt49AOM/Zz0H4qfs0/EzRfiJ+0DPrWj6b+2vov9sfE2z1vxLDrXhz9nf8Aa38QfHX4i+NPhh8EvE3ivSNW8Q6J4+1rxd8NPjz4W/Yw8C/HO5HhHwfqVh+xx+zV8E9Au7nVvir+zP8ABPRQD4Z/br/Z2/aU8ffGf4ueBNF+FHwZ8f6N8Wv2mfjV+0T+z98PPGui+APiv4p+JPjHwV/wQxvvgH4L+K/ijwD8Q1vfhT8Pfgz8F/2mtH+HHw/uNa+MGjeI9K8WfGb4ofClfEL/AAw0HSfAF38TwA/4J1fDjwV8Iv8Ago/rPw7s/AHgz4ZePPD/AMGf2wftPga78K/8E6/Bv7SmifBzVU/4I8a38Of+GjPDP/BPXT9M+G+mf2n8SNS+Pmr/AAh/4Suyi8VXngC9TUV82yuTcOAeZ/BP4NX3x6h/YZ+Dvxp/4J+ftAaB4g8M/wDBLP4k/sPfFn4nQfB/w34V+IHwd8KfFb4U/sO/Dvw98U9V+M/xy8OeCfhpp2teH9E8RftUDQPhR8FvHvx2+NngLwL4m8T23j74QeH/AIo+LP2hf2dvDIB+pfwu/bR+LXjb9kv9pXxp4/8ABPgz4JftI/s5/wBg/BLXNF8eaR8Ubv4c6v8Ate6z+y9+z34+h8O2HgTw1pOsfE7xL4M1b4/fG+D4PfDXw38Ir/4n+J/jnoNh4T8Tfs9eLfH0vxG+HouQD7m+CmqfFTXPgt8Idb+OnhrRfB3xn1j4ZeANU+MPhDw3PDdeHPCvxUv/AAnpF38QfDWgXVv4h8VQXOi6F4pl1TTNKng8Ta/DLYW9vJF4h1WNhqcoB+QH7e3gf4Eav8d/jBrWkzfBn4Pf8LJ/Yz/aV/Y1/a//AGrfEeh23hDwd8OfGP7UFz+x9a/Bn/hoD4x6b4WfS9Y8Z+C/gX8OPiX4t+GfgT4keLtC0pdVv/2ePgx4t+IPwoP7Tf7N/ifXgA+BP7RX7L9x+1R8Jvi94U/aH/YzudZ+PPjP4v8Aw5g/Zw8I/tdfBDWvFP7Oul/Huz8HfEK68YaJD4f+OuueGfiP8Zv2jfjV8Dfhro37RPwj+D3hXXPBk/xm+I/h3xr8EfE15r3hT9r/APaU/aJAPM/APxD1LQvE1l46+GHjX4m+BfiH8GP2gP8Agsd8KdeTVP8AglX/AMFBf2v/AIV+KvDnx4/4Kc618RGvfDXjj9nub4a+Fv7a8Mz/AAP0OzuLnR/HPiuwhn1nxD4b1zTNM8T6PcQxAHs37HuqaRa/tdfs8eAo9a+JvinxXpX7P/8AwVk+K3jrxb46/Y0/af8A2QfDmseI/wBpD9vn9iv4639l8PvBX7RmhLqOo6L4a1HxjqXh9rbRPG3ja/0iws9DvvFuqWdzr2kRTAH4z3WnfAr4m337JWl+LvjB+wz4f1L4e/8ABID/AIJX+G9W079qb46/sSfD6+0nxHqnhv8AaK8XXGgaBaftQf8ABOf9vic61F4W8QeDPFHibSvC0fwvv7Hw34o+Guu+KbfxnpXiPwHe6QAfcv7P+j/sv/ED/gnH8CPAMfgL9mb9qH4l/DP9sz9oyy0jwr4Q+EfwQ/a6/wCEX+BHhX/gpdN8S/2wtQ+Gnhzwd+z3pug6Z4M1P9lHUtK1PR9Y+HPwS+Ftt42/4T39mrwr8C/htonxa+KX7Lnwv1AA4z4b6H/wSjvv2nP2s9Tsvip/wQ08MfAez/aZ+Gn/AAjPgn4t/AH9mrxz4p8RfDm3/ZF/ZEuPHP8Awoj4oRftO/Djwf4K8Gax4wfx9pNh/Z/wm8Z6D4e+M0HxO8S61/wkes3XiDwlbgH9IWi6J4M8SfBHw7oHwG8Zad4A+H2rfDvw7Y/B/wAbfAq1+GN5oPhjwHN4fsV8C6z8I7TVfCfjb4XXHh2z8PnTZPBkUnhTXPBLaINOFnpNzpXkoca1KdWChTxFbDSVbD1HVoxw8pyhRxFKtUw7WJoYin7HGU6csLiJRhHERw9WrLCV8PjFRxUNKU4U5OU6NOunTrU1Cq6yjGVSjUpQrJ0K1GftMNOUcRRUpOjKtCnHE0a2HdWhLxH46/Bb4m6z+w1+0t8DtL+IHjv9oD4l+OfgN8dvBvhXxF8SE+DPhrxb4p8S+M/APivSvDGgXs3w6+H/AMGvhrpdul9qVlpFjqEnhzSIIbQx3XiLVp5VvNVPm8T4avmWRzwWCwlN4hSwC9ypKEsV7HOKeLqV60sTiXRhUp4f90o0PY0pUaNH9zPFyr1qnt8E4zDZNxjw/m2ZY2rHAYTiPJsxxE6lKNSGAwWFxWCeIjQp4TC/Wa1JLD1cZKNT61i5V69enSqPDxwuFh9K/D/S7/Q/AHgfRNUg+y6lpHhHwzpeo23mwzfZ7+w0WztbuDzreWWCbyZ4nTzYJZIZMb4pXjIY/ZcR4vD4/iPiDH4Sp7XC43Os0xeFq8lSHtcPiMfjK1Gp7OrGFSHPTqQlyVIxqR5uWcVNSR8HwtgsTlvC/DmXY2n7HGYDIsmwWLo89Op7LE4XLqNDEU/aUp1KVT2dSDjz05zpy+KE5RfM/wCbD4iaP+2/q/xX+GfwP+H/AMK/2gPB3h+P4m/8Fn/iXZ6V4N+J3jbwR4j+Lthf/wDBSTwP4o8PfFi6+Hvgf/gor/wTfnh+GWgeFvjPoGnfCvxr4y+Pev8AiTxbf+MviNqOifs0p8Lm+HP7QmteKe6e/wD7A3jbxj8d/wBjb9tb4f6n4V8Z/to+HPiN+0z4t/Z4g8PS/tL6Vqvg7QPhj8QP2K/2dP8AhJF8T/G/Xv2/v23PEXhX4M2niLxV4k/4Tq8+Bf7Qn7QXxd8Lar4t17Xfh58HE8V2+s/CfRQDa8OfBiw+PX7YX7bFp4S/aV0X9o74neDv2f8A9i/Qvj/4ZuPit4kg/Zl+Jnjey+P37fk3xS/Y7+LXwk8CeL/HOnfA34ZQ6d4P8HeENN8ExWHiv4hfD3R2vrr9oXTP2jfDvxb/AG3vht8bADs/22/2gvhPoniP4D+L/Gni34m/smftR6jouual4N8LfED9sz/gm58PofgtpEeofEP4e+HvHPxU+Gf7RXxn/aP+DnhzRfiSnifxfb3X7Sn7KfwF+NP7T+m/CjTfG/wt/tVtPuPEf7PuvAGz+zB8PLz40aVd+D/Fv7PPxm8PeA9b8ZwePda8ea38Qf2rvhP4l+Dnjrwv4N8R+ED4h8C/tOftFeGPgf8A8FMP2jf2mfiP4e1zwn4E8T/FbV/CHwr/AGb9A/Y8eH9lL4e/Ee6vfhR8S/AvxPAPk34lWWr6T+2v8ctQ8L/HHWviB+zjaa14c8M/Eu90T4bfswfFb9jT4C/CLXf2sm8cf8FNPgX+2V4gtf2KNI074Ha18XdO+I0Wkaz4E+Gfj34h/tBaonhHSP2r/wDgp/8AFrwt+z/4Y8K/EfwcAfWXwl0T4EfA/wDb2+LvxTg8deDPhr8B9D8GfGbwn8O9afwxbfD74EW3jHRvgt+w34A+P/wG8I+O5ptL+Helf8Md/Dv/AIJs+HPGWqJpd0mla/bePP2gPht4d0nRtZ/Ym/aoa3APoC58C+I/iH4c/bJ/ai8T/ELWv2UJ/if8Mvh34Z+CXxJ8TLf+CdX+CnwW/ZqsfiN8T/hZ8ffjp4F8Qa74ZfStab4x/Ff4ufEvx18JPiP4n0fwl4i/ZgtvhL8G/wBpv4W+C/Gkv7Sfw4lAMX4efE34EeEvD37bXxt+Nvxm8GfCD9nj9rH9pnTpfgv8Y/FvxktvgbpfxM8HP+w9+zF8GJvE/wALPiTL4u8D+ItK/wCEh8RfBP4oah8J/HXhLWtL1Xxf4M0HTPj18Fde1X4Zax4A+JN8AfDXhv4lf8Eybj9q741eFtX/AOCl2tL8GNH/AGf/ANmHXvh9dT/8Fq/2o7Xw5N8VPEXxL/bG074xQaV4wX9tqGfXtatvC3hT4JSeIPDUus30PhawufC+rW+k6XJ4rur7UgD9ZfjhYeHP21v2H/2hfB/7O/xM+GXjzTf2hf2f/jx8Kfhx8StB8Z2Hif4V3niPxZ4K8c/DaG9uPGPghPFcFzouheKbibT/ABLc6Fbaxf6bPp+r2SaZcataSaawB+Jvx0+Cf7Rem/G79pP4o+JviZ8GfEniP4UfGb9mf4n/ABD+NPhP4U+PNE+CHw+/4WZ+0J/wRK1WxuPHPw4v/jH4pvvD3/DMvwt/4Jx+Nvj7+1f4X1z4/wCj/wDCN/s9/EX9nr40Q+O9A8K/ErXI/BoB+v8A+x78evgF8RfEfxa+E3wb/aj1r9sLxB4C0X4Y/FD4mfHif4m/B/4keHNRvvi1f/FDwZ4V8GaVL8Eh4e+GngHWvCOifATzfEHw88B/DPwD4bWw1nwv8TNUtPEvxR8e/E/xreAHwz/wVBi0uf8AZz/b5+KfxYuf+GSfC+u/szfEr4AaJf8AjL4z+DPDHxG/bT8e/DLwJ+054w/Z58DeE38I/FXXtH8L/BldY+IXjfxnoPw50u60f9pn9pjXtXvPht8cvA3g39nv4feJPhT8VgD7m8C+F779k7x/8QtR8XaTrXjf4T+LtFXxFq37Xnjz4peG7zxH8DvhX8IvA2vXemfDD9pzxT8Y/ibpvinVfhl8OII9Uufg78Rvhra+J5tZv/GnxQ8aftSeEtH+Nb/Gn9s34wgHjPxS+FPiP9ubWvEXx5+F97otl4f+FPwy0Gw/Yd8XeI7m/wBL8EfGX40aL+0X8FP2o9R+JnjlNO0zxJrfiL9kzXPiX+yZ+y/4T+HvjPwdH4X8W/ET4ej9qDx18PU8RfC7xn+yl8eboAxf28viP8Of2m/+CdH7eOhaH4/8Z+AfHHwd/Zm+Ofij43/AOz8VaD4S+MXgnXbT4EfGG78KfDX4/wCjeHdR1vxFongzW/EWjw+NNFv/AAP4ptvhv+0L4V8L6bd+FfiF8Vf2RPGnifS/FoB8AfGr9hjxj4S/bB8HT+I/2Qv2Zv2ovDnxX/b/AFHwo+KP7Y3xY0rWvi18TPhjoP8AwSC8baRpvwR+JGt2/wCyN8cfEV98GfhV4i+FXifVfB3jH4m+IvF3xd8X/FH4e+FfEXxM8Fa/4r8Q3/7Vt+Aff/gj4H/H/wCGn7NH/BLj4P8AhO78Z/AH4q3fgz9nb9nv9rn4o/CLwN8DfHnxG8JfDn4I/sK/tI+JtO8H+IvG3jv4W/G7wDbeDPD/AO0FYaH4e0jxBdabqWgw69421XRvA2uWupePJbq+APmb9l3Rfidpnin4T/BS9+L/AO0z8ffhp8WP2zP+Co3hH9pf4V/Ef9l34ReJ/wBlfUvgQPjP/wAFNtD1TxJ4/wDjL4O/Yx8L+GdG8Z/EL41+Bvh/c+KvhtqHxptLbUbzx5qGh6L8H9N+EviDwj4aswD2bx1eeDtd/wCCpHgaf43/ALXngzWv+Gcf2Zv2yvi1q/w/+FPxD1X9nvS/2aPAWjfGP/gn5458B3n7Tq+FPjjqvjDxl/wmXg/U7/VfiS3xr8RaR+zN8TfBng3wh4i0H9mzwqdI8b+INbAMb4AX/iP4K/tXfBZvHfwz+Jt3of7THxN/a78L/ss+IPF3gy/8BQ/D74f/ALQPxL/bK/4KD/H7xR49060fXNR8KfE34gaj8Mv2dPh/o/7P3xyg8L+OdO+FHhHwf8bPBOoeH/i9q37dv7IPw0AP2xoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgAoAKACgD/9k=";
		//System.out.println(httpArg);
		String strImg = GetImageStr();  
		strImg = strImg.replaceAll("\r\n","");  
		strImg = strImg.replaceAll("\\+","%2B");
		httpArg+="&image="+strImg;
		System.out.println(httpArg);
		//System.out.println(strImg);
		String request = request(httpUrl,httpArg);
		//System.out.println(request);
		GenerateImage(strImg);
		
	}
	//base64字符串转化成图片  
    public static boolean GenerateImage(String imgStr)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = "d://222.jpg";//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
    
  //图片转化成base64字符串  
    public static String GetImageStr()  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        String imgFile = "d://通知.jpg";//待处理的图片  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    } 
    
    /**
     * @param urlAll
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "1518525f402a134c823362eb99b039db");
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
