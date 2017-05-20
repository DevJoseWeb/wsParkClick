/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ce.saude.ponto.util;

import java.math.BigInteger;

/**
 *
 * @author joao
 */
public class TipoRegime {

    public static BigInteger getId(Boolean diarista, 
            Boolean plantonista, Boolean horaExtra) {

        if (diarista && plantonista && horaExtra) {
            return new BigInteger("7");
        } else if (!diarista && plantonista && horaExtra) {
            return new BigInteger("6");
        } else if (diarista && !plantonista && horaExtra) {
            return new BigInteger("5");
        } else if (diarista && plantonista && !horaExtra) {
            return new BigInteger("4");
        } else if (!diarista && !plantonista && horaExtra) {
            return new BigInteger("3");
        } else if (!diarista && plantonista && !horaExtra) {
            return new BigInteger("2");
        } else if (diarista && !plantonista && !horaExtra) {
            return new BigInteger("1");
        }

        return null;
    }
}
