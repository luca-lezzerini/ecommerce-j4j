import { LoginResponseDto } from './login-response-dto';
import { Taglia } from './taglia';

export class ProdottoTagliaResultsDto extends LoginResponseDto {
  listaSelezionati: Taglia[];
  listaDisponibili: Taglia[];
}
