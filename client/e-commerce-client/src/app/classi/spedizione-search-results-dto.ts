import { Spedizione } from './spedizione';

export class SpedizioneSearchResultsDto{
  result: Spedizione[];
  numeroPagina: number;
  ultimaPagina: boolean;
}
