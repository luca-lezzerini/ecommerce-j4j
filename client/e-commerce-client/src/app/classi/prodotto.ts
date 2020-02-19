import { RigaOrdine } from './riga-ordine';

export class Prodotto {
  id: number;
  codice: string;
  descrizione: string;
  prezzo: number;
  offerta: boolean;
  righe: RigaOrdine[];
}
