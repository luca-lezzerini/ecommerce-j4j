import { LoginResponseDto } from './login-response-dto';

export class OrdineSearchDto extends LoginResponseDto {
  stato: string;
  searchData: Date;
  searchNumeroOrdine: number;
}
