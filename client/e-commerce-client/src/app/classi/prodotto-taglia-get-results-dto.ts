import { LoginResponseDto } from './login-response-dto';
import { Taglia } from './taglia';
import { Prodotto } from './prodotto';

export class ProdottoTagliaGetResultsDto extends LoginResponseDto {
  result: Prodotto[];
  taglia: Taglia;
  prodotto: Prodotto;
}
